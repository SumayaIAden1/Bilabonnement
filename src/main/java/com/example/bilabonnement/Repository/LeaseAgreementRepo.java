package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.LeaseAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

//Persistenslaget: det er her vi har forbindelse til vores database

@Repository
public class LeaseAgreementRepo
{
    @Autowired
    JdbcTemplate template; //automatisk oprettelse af instans, Jdbc template forenkler Sql adgang (vi behøves ikke skrive try/catch selv)

    public List<LeaseAgreement> fetchAll()
    {
        String sql = "select * from lease_agreement";
        RowMapper<LeaseAgreement> rowmapper = new BeanPropertyRowMapper<LeaseAgreement>(LeaseAgreement.class); //RowMapper: mapper én række fra databasen til et Java-objekt.
        return template.query(sql, rowmapper);                                                                 //BeanPropertyRowMapper bruger navnene på kolonnerne i databasen og matcher dem automatisk til felter i din LeaseAgreement-klasse ved navn.
    }                                                                                                          //template.query() eksekverer SQL’en og returnerer en liste af objekter, mappet vha. rowmapper.

    public void addLeaseAgreement(LeaseAgreement leaseAgreement)
    {
        String sql = "insert into lease_agreement(lease_id, start_date, end_date, monthly_price, total_price, lease_type, status, car_id, customer_id, location_id) values(?,?,?,?,?,?,?,?,?)";
        template.update(sql,
                leaseAgreement.getLeaseId(),
                leaseAgreement.getStartDate(),
                leaseAgreement.getEndDate(),
                leaseAgreement. getMonthlyPrice(),
                leaseAgreement.getTotalPrice(),
                leaseAgreement.getLeaseType(),
                leaseAgreement.getStatus(),
                leaseAgreement.getCarId(),
                leaseAgreement.getCustomerId(),
                leaseAgreement.getLocationId());
    }

    public LeaseAgreement findLeaseAgreementById(int id)
    {
        String sql = "select * from lease_agreement where id = ?";
        RowMapper<LeaseAgreement> rowMapper = new BeanPropertyRowMapper<>(LeaseAgreement.class);
        LeaseAgreement leaseAgreement = template.queryForObject(sql, rowMapper, id);
        return leaseAgreement;
    }

    public Boolean deleteLeaseAgreement(int id)
    {
        String sql = "delete from lease_agreement where id = ?";
        return template.update(sql, id) > 0;
    }

    public void updateLeaseAgreement(int id, LeaseAgreement leaseAgreement)
    {
        String sql = "UPDATE lease_agreement SET start_date = ?, end_date = ?, monthly_price = ?, total_price = ?, lease_type = ?, status, car_id = ?, " +
                "customer_id = ?, location_id = ? where lease_id = ?";
        template.update(sql,
                leaseAgreement.getStartDate(),
                leaseAgreement.getEndDate(),
                leaseAgreement. getMonthlyPrice(),
                leaseAgreement.getTotalPrice(),
                leaseAgreement.getLeaseType(),
                leaseAgreement.getStatus(),
                leaseAgreement.getCarId(),
                leaseAgreement.getCustomerId(),
                leaseAgreement.getLocationId());
    }


}
