package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.LeaseAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LeaseAgreementRepo
{
    @Autowired
    JdbcTemplate template;

    public List<LeaseAgreement> fetchAll()
    {
        String sql = "select * from lease_agreement";
        RowMapper<LeaseAgreement> rowmapper = new BeanPropertyRowMapper<>(LeaseAgreement.class);
        return template.query(sql, rowmapper);
    }

    public void addLeaseAgreement(LeaseAgreement leaseAgreement)
    {
        String sql = "INSERT INTO lease_agreement (lease_id, start_date, end_date, start_mileage, end_mileage, monthly_price, total_price, lease_type, status, car_registration_number, user_id, customer_id, location_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        template.update(sql,
                leaseAgreement.getLeaseId(),
                leaseAgreement.getStartDate(),
                leaseAgreement.getEndDate(),
                leaseAgreement.getStartMileage(),
                leaseAgreement.getEndMileage(),
                leaseAgreement.getMonthlyPrice(),
                leaseAgreement.getTotalPrice(),
                leaseAgreement.getLeaseType(),
                leaseAgreement.getStatus(),
                leaseAgreement.getCarRegistrationNumber(),
                leaseAgreement.getUserId(),
                leaseAgreement.getCustomerId(),
                leaseAgreement.getLocationId()
        );
    }

        //Isabella: til dashboard - se hvor mange biler der er udlejet

    public int countActiveLeases()
    {
        String sql = "SELECT COUNT(*) FROM lease_agreement WHERE status = 'Active'";
        return template.queryForObject(sql, Integer.class);
    }

    //Til dashboard - se pris på nuværende udlejede biler

    public boolean deleteLeaseAgreement(int id) {
        String sql = "DELETE FROM lease_agreement WHERE lease_id = ?";
        return template.update(sql, id) > 0;
    }

    public void updateLeaseAgreement(int id, LeaseAgreement leaseAgreement) {
        String sql = "UPDATE lease_agreement SET start_date = ?, end_date = ?, start_mileage = ?, end_mileage = ?, monthly_price = ?, total_price = ?, lease_type = ?, status = ?, car_registration_number = ?, user_id = ?, customer_id = ?, location_id = ? WHERE lease_id = ?";

        template.update(sql,
                leaseAgreement.getStartDate(),
                leaseAgreement.getEndDate(),
                leaseAgreement.getStartMileage(),
                leaseAgreement.getEndMileage(),
                leaseAgreement.getMonthlyPrice(),
                leaseAgreement.getTotalPrice(),
                leaseAgreement.getLeaseType(),
                leaseAgreement.getStatus(),
                leaseAgreement.getCarRegistrationNumber(), // ✅ Korrekt felt
                leaseAgreement.getUserId(),
                leaseAgreement.getCustomerId(),
                leaseAgreement.getLocationId(),
                id);
    }


    public LeaseAgreement findActiveLeaseByRegistrationNumber(String regNumber) {
        String sql = "SELECT * FROM lease_agreement WHERE car_registration_number = ? AND status = 'Active'";
        return template.queryForObject(sql, new LeaseAgreementRowMapper(), regNumber);
    }

    // Intern klasse til mapping af LeaseAgreement
    private static class LeaseAgreementRowMapper implements RowMapper<LeaseAgreement> {
        @Override
        public LeaseAgreement mapRow(ResultSet rs, int rowNum) throws SQLException {
            LeaseAgreement lease = new LeaseAgreement();
            lease.setLeaseId(rs.getInt("lease_id"));
            lease.setStartDate(rs.getDate("start_date"));
            lease.setEndDate(rs.getDate("end_date"));
            lease.setStartMileage(rs.getInt("start_mileage"));
            lease.setEndMileage(rs.getInt("end_mileage"));
            lease.setMonthlyPrice(rs.getDouble("monthly_price"));
            lease.setTotalPrice(rs.getDouble("total_price"));
            lease.setLeaseType(rs.getString("lease_type"));
            lease.setStatus(rs.getString("status"));
            lease.setCarRegistrationNumber(rs.getString("car_registration_number"));
            lease.setUserId(rs.getInt("user_id"));
            lease.setCustomerId(rs.getInt("customer_id"));
            lease.setLocationId(rs.getInt("location_id"));
            return lease;
        }
    }



    public LeaseAgreement findById(int id) {
        String sql = "SELECT * FROM lease_agreement WHERE lease_id = ?";
        RowMapper<LeaseAgreement> rowMapper = new BeanPropertyRowMapper<>(LeaseAgreement.class);
        return template.queryForObject(sql, rowMapper, id);
    }



    public double sumTotalPriceOfLeasedCars()
    {
        String sql = "SELECT SUM(total_price) FROM lease_agreement WHERE status = 'active'";
        Double result = template.queryForObject(sql, Double.class); //Isabella - Jdbc sætning der eksekverer SQL'en , Double.class fortæller Spring at det er decimaltal som værdi
        return result != null ? result : 0.0; // ? er en kort måde at skrive if/else vi kunne også skrive:
        /* - Isabella -

        if (result != null) {

         return result;
        } else {

        return 0.0;
        }
    }


    public LeaseAgreement findLeaseAgreementById(int id) {
        String sql = "select * from lease_agreement where lease_id = ?";
        RowMapper<LeaseAgreement> rowMapper = new BeanPropertyRowMapper<>(LeaseAgreement.class);
        return template.queryForObject(sql, rowMapper, id);
    }




    /*Tjekker for om bilen er available i den givne periode
    public boolean isCarAvailable(int carId, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT COUNT(*) FROM lease_agreement " +
                "WHERE car_id = ? AND (" +
                "(start_date <= ? AND end_date >= ?) OR " +
                "(start_date <= ? AND end_date >= ?)" +
                ")";
        Integer count = template.queryForObject(sql, Integer.class, carId, endDate, startDate, startDate, endDate);
        return count == 0;
    }*/


    }
}
