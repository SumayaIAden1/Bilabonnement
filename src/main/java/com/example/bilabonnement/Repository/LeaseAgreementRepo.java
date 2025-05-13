package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.LeaseAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class LeaseAgreementRepo {
    @Autowired
    JdbcTemplate template;

    public List<LeaseAgreement> fetchAll() {
        String sql = "select * from lease_agreement";
        RowMapper<LeaseAgreement> rowmapper = new BeanPropertyRowMapper<>(LeaseAgreement.class);
        return template.query(sql, rowmapper);
    }

    public void addLeaseAgreement(LeaseAgreement leaseAgreement) {
        String sql = "insert into lease_agreement(lease_id, start_date, end_date, monthly_price, total_price, lease_type, status, car_id, user_id, customer_id, location_id) " +
                "values(?,?,?,?,?,?,?,?,?,?,?)";
        template.update(sql,
                leaseAgreement.getLeaseId(),
                leaseAgreement.getStartDate(),
                leaseAgreement.getEndDate(),
                leaseAgreement.getMonthlyPrice(),
                leaseAgreement.getTotalPrice(),
                leaseAgreement.getLeaseType(),
                leaseAgreement.getStatus(),
                leaseAgreement.getCarId(),
                leaseAgreement.getUserId(),
                leaseAgreement.getCustomerId(),
                leaseAgreement.getLocationId());
    }

    public LeaseAgreement findLeaseAgreementById(int id) {
        String sql = "select * from lease_agreement where lease_id = ?";
        RowMapper<LeaseAgreement> rowMapper = new BeanPropertyRowMapper<>(LeaseAgreement.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    public Boolean deleteLeaseAgreement(int id) {
        String sql = "delete from lease_agreement where lease_id = ?";
        return template.update(sql, id) > 0;
    }

    public void updateLeaseAgreement(int id, LeaseAgreement leaseAgreement) {
        String sql = "UPDATE lease_agreement SET start_date = ?, end_date = ?, monthly_price = ?, total_price = ?, lease_type = ?, status = ?, car_id = ?, user_id = ?, " +
                "customer_id = ?, location_id = ? WHERE lease_id = ?";
        template.update(sql,
                leaseAgreement.getStartDate(),
                leaseAgreement.getEndDate(),
                leaseAgreement.getMonthlyPrice(),
                leaseAgreement.getTotalPrice(),
                leaseAgreement.getLeaseType(),
                leaseAgreement.getStatus(),
                leaseAgreement.getCarId(),
                leaseAgreement.getUserId(),
                leaseAgreement.getCustomerId(),
                leaseAgreement.getLocationId(),
                id);
    }
}
