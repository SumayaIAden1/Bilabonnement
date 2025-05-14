package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class CustomerRepo {

    @Autowired
    private JdbcTemplate template;

    // RowMapper to map SQL result to Customer object
    private final RowMapper<Customer> customerRowMapper = new BeanPropertyRowMapper<>(Customer.class);

    // Find all customers
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customers";  // Assuming your table name is 'customers'
        return template.query(sql, customerRowMapper);
    }

    // Add a new customer
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (customer_id, name, email, phone_number, cpr_number, address_id, created_at, is_active) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, customer.getCustomerId(), customer.getName(), customer.getEmail(),
                customer.getPhoneNumber(), customer.getCprNumber(), customer.getAddressId(),
                customer.getCreatedAt(), customer.isActive());
    }

    // Update an existing customer
    public boolean updateCustomer(int customerId, Customer updatedCustomer) {
        String sql = "UPDATE customers SET name = ?, email = ?, phone_number = ?, cpr_number = ?, address_id = ?, created_at = ?, is_active = ? WHERE customer_id = ?";
        int rowsUpdated = template.update(sql, updatedCustomer.getName(), updatedCustomer.getEmail(),
                updatedCustomer.getPhoneNumber(), updatedCustomer.getCprNumber(),
                updatedCustomer.getAddressId(), updatedCustomer.getCreatedAt(),
                updatedCustomer.isActive(), customerId);
        return rowsUpdated > 0;  // Returns true if at least one row was updated
    }

    // Delete a customer by their ID
    public boolean deleteById(int customerId) {
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        int rowsDeleted = template.update(sql, customerId);
        return rowsDeleted > 0;  // Returns true if the customer was deleted
    }
}
