package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.Model.Car.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CarRepo {

    @Autowired
    JdbcTemplate template;

    // Henter alle biler
    public List<Car> fetchAll() {
        String sql = "SELECT * FROM car";
        return template.query(sql, new CarRowMapper());
    }

    // Tilføjer ny bil
    public void addCar(Car car) {
        String sql = "INSERT INTO car (registration_number, vin_number, status, color, purchase_price, registration_fee, mileage, location, model_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        template.update(sql,
                car.getRegistrationNumber(),
                car.getVinNumber(),
                car.getStatus().name(),  // enum → string
                car.getColor(),
                car.getPurchasePrice(),
                car.getRegistrationFee(),
                car.getMileage(),
                car.getLocation(),
                car.getModelId());
    }

    // Find bil ud fra registration_number (primær nøgle)
    public Car findCarByRegistration(String regNumber) {
        String sql = "SELECT * FROM car WHERE registration_number = ?";
        return template.queryForObject(sql, new CarRowMapper(), regNumber);
    }

    // Slet bil
    public boolean deleteCar(String regNumber) {
        String sql = "DELETE FROM car WHERE registration_number = ?";
        return template.update(sql, regNumber) > 0;
    }

    // Opdater bil
    public void updateCar(String regNumber, Car car) {
        String sql = "UPDATE car SET vin_number = ?, status = ?, color = ?, purchase_price = ?, registration_fee = ?, mileage = ?, location = ?, model_id = ? " +
                "WHERE registration_number = ?";

        template.update(sql,
                car.getVinNumber(),
                car.getStatus().name(),
                car.getColor(),
                car.getPurchasePrice(),
                car.getRegistrationFee(),
                car.getMileage(),
                car.getLocation(),
                car.getModelId(),
                regNumber);
    }

    // Intern klasse der håndterer mapping fra ResultSet → Car-objekt med enum
    private static class CarRowMapper implements RowMapper<Car> {
        @Override
        public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
            Car car = new Car();
            car.setRegistrationNumber(rs.getString("registration_number"));
            car.setVinNumber(rs.getString("vin_number"));
            car.setStatus(CarStatus.valueOf(rs.getString("status")));
            car.setColor(rs.getString("color"));
            car.setPurchasePrice(rs.getDouble("purchase_price"));
            car.setRegistrationFee(rs.getDouble("registration_fee"));
            car.setMileage(rs.getInt("mileage"));
            car.setLocation(rs.getString("location"));
            car.setModelId(rs.getInt("model_id"));
            return car;
        }
    }

    //Metode til at vise bilerne - dropdown
    public List<String> fetchAllRegistrationNumbers() {
        String sql = "SELECT registration_number FROM car";
        return template.queryForList(sql, String.class);
    }

    //-----------------------------------------------------------------------------------------------------------------

    //User story 6: Lageroversigt

    public List<Car> fetchAllLeasedCars()
    {
        String sql = "select c.*" +
                "from car c" +
                "join lease_agreement l on c.car_id = l.car_id" +
                "where current_date between l.start_date and l.end_date";

        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return template.query(sql, rowMapper);
    }

    //------------------------------------------------------------------------------------------------------------------
}
