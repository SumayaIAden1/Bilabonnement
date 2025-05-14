package com.example.bilabonnement.Repository;
import com.example.bilabonnement.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class));
    }

    public Customer findById(int id) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        List<Customer> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class), id);
        return result.isEmpty() ? null : result.get(0);
    }

    public void save(Customer customer) {
        String sql = "INSERT INTO customer (first_name, last_name, email, phone_number, cpr_number, address_id, created_at, is_active) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getCprNumber(),
                customer.getAddressId(),
                customer.getCreatedAt(),
                customer.isActive());
    }

    public boolean update(int id, Customer customer) {
        String sql = "UPDATE customer SET first_name = ?, last_name = ?, email = ?, phone_number = ?, cpr_number = ?, address_id = ?, created_at = ?, is_active = ? WHERE customer_id = ?";
        int rows = jdbcTemplate.update(sql,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getCprNumber(),
                customer.getAddressId(),
                customer.getCreatedAt(),
                customer.isActive(),
                id);
        return rows > 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    public Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM customers WHERE customer_id = ?";
        return template.queryForObject(sql, customerRowMapper, customerId);
    }
}
