package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Address;
import com.example.bilabonnement.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepo {

    private final JdbcTemplate template;

    // RowMapper to map SQL result to Customer objects (no need for Address RowMapper)
    private final RowMapper<Customer> customerRowMapper = new BeanPropertyRowMapper<>(Customer.class);

    @Autowired
    public CustomerRepo(JdbcTemplate template) {
        this.template = template;
    }

    // Fetch all customers
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer";
        return template.query(sql, customerRowMapper);
    }

//    public void addAddress(Address address) {
//        String sql = "INSERT INTO address (street, city, zip, country) VALUES (?, ?, ?, ?)";
//        template.update(sql, address.getStreet(), address.getCity(), address.getZip(), address.getCountry());
//    }

    public void addAddress(Address address) {
        String sql = "INSERT INTO address (street, city, zip, country) VALUES (?, ?, ?, ?)";
        // Insert address and retrieve the generated address_id
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, address.getStreet());
            ps.setString(2, address.getCity());
            ps.setString(3, address.getZip());
            ps.setString(4, address.getCountry());
            return ps;
        }, keyHolder);

        // Set the generated address_id back to the address object
        address.setAddressId(keyHolder.getKey().intValue());
    }


//    public void addCustomer(Customer customer) {
//        String sql = "INSERT INTO customer (customer_name, email, phone_number, cpr_number, address_id, is_active) VALUES (?, ?, ?, ?, ?, ?)";
//        template.update(sql, customer.getName(), customer.getEmail(), customer.getPhoneNumber(),
//                customer.getCprNumber(), customer.getAddress().getAddressId(), customer.isActive());
//    }

    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customer (customer_name, email, phone_number, cpr_number, address_id, is_active) VALUES (?, ?, ?, ?, ?, ?)";
        template.update(sql,
                customer.getName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getCprNumber(),
                customer.getAddress().getAddressId(),  // Use the generated address_id
                customer.isActive());
    }


    // Delete a customer by their ID
    public boolean deleteById(int customerId) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        int rowsDeleted = template.update(sql, customerId);
        return rowsDeleted > 0;
    }

    // Get a customer by their ID
    public Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";  // Simple query without JOIN
        return template.queryForObject(sql, customerRowMapper, customerId);  // Map the result directly to a Customer object
    }

    public Optional<Customer> findByEmail(String email) {
        String sql = "SELECT * FROM customer WHERE email = ?";
        try {
            return Optional.ofNullable(template.queryForObject(sql, customerRowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty(); // Return an empty Optional if no customer is found
        }
    }

    public Customer getLastCreatedCustomer() {
        String sql = "SELECT * FROM customer ORDER BY created_date DESC LIMIT 1";  // Fetch the most recently created customer
        return template.queryForObject(sql, customerRowMapper);  // Map the result directly to a Customer object
    }
}

