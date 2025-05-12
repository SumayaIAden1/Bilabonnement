package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepo
{
    @Autowired
    JdbcTemplate template;
    public List<Car> fetchAll()
    {
        String sql = "select * from car";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return template.query(sql, rowMapper);
    }

    public void addCar(Car car)
    {
        String sql = "insert into car(car_id, model, registration_number, status, brand, vin_number, color, equipment_level, co2_emission, purchase_price, registration_fee, mileage, location) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, car.getCarId(),
                car.getModel(), car.getRegNumber(),
                car.getStatus(),
                car.getBrand(),
                car.getVinNumber(),
                car.getColor(),
                car.getEquipmentLevel(),
                car.getCo2Emission(),
                car.getPurchasePrice(),
                car.getRegFee(),
                car.getMileage(),
                car.getLocation());

    }

    public Car findCarById(int id)
    {
        String sql = "select * from car where id = ?";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<> (Car.class);
        Car car = template.queryForObject(sql, rowMapper, id);
        return car;
    }

    public Boolean deleteCar(int id)
    {
        String sql = "delete from car where id = ?";
        return template.update(sql, id) > 0;
    }

    public void updateCar(int id, Car car)
    {
        String sql = "update car set model = ?, registration_number = ?, status = ?, brand = ?, vin_number = ?, color = ?, equipment_level = ?, co2_emission = ?, purchase_price = ?, registration_fee = ?, mileage = ?, location = ? where id = ?";
        template.update(sql,
                car.getModel(),
                car.getRegNumber(),
                car.getStatus(),
                car.getBrand(),
                car.getVinNumber(),
                car.getColor(),
                car.getEquipmentLevel(),
                car.getCo2Emission(),
                car.getPurchasePrice(),
                car.getRegFee(),
                car.getMileage(),
                car.getLocation(),
                car.getCarId());

    }
}
