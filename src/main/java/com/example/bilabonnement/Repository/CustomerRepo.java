package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Address;
import com.example.bilabonnement.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepo {

    private final JdbcTemplate template;

    /**
     * Custom mapper that populates both Customer and its nested Address.
     */
    private final RowMapper<Customer> customerWithAddressMapper = (rs, rowNum) -> {
        Address address = new Address();
        address.setAddressId(rs.getInt("address_id"));
        address.setCountry(rs.getString("country"));
        address.setCity(rs.getString("city"));
        address.setZip(rs.getString("zip"));
        address.setStreet(rs.getString("street"));

        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setCustomerName(rs.getString("customer_name"));
        customer.setEmail(rs.getString("email"));
        customer.setPhoneNumber(rs.getString("phone_number"));
        customer.setCprNumber(rs.getString("cpr_number"));
        customer.setActive(rs.getBoolean("is_active"));
        customer.setAddress(address);

        return customer;
    };

    @Autowired
    public CustomerRepo(JdbcTemplate template) {
        this.template = template;
    }

    /**
     * Fetch all customers along with their address.
     */
    public List<Customer> findAllWithAddress() {
        String sql = """
            SELECT
              c.customer_id,
              c.customer_name,
              c.email,
              c.phone_number,
              c.cpr_number,
              c.is_active,
              a.address_id,
              a.country,
              a.city,
              a.zip,
              a.street
            FROM customer c
            JOIN address a ON c.address_id = a.address_id
            """;
        return template.query(sql, customerWithAddressMapper);
    }

    /**
     * Search customers by name, email or phone (case-insensitive).
     */
    public List<Customer> searchWithAddress(String keyword) {
        String sql = """
        SELECT
          c.customer_id,
          c.customer_name,
          c.email,
          c.phone_number,
          c.cpr_number,
          c.is_active,
          a.address_id,
          a.country,
          a.city,
          a.zip,
          a.street
        FROM customer c
        JOIN address a ON c.address_id = a.address_id
        WHERE LOWER(c.customer_name) LIKE ?
           OR LOWER(c.email)         LIKE ?
           OR LOWER(c.phone_number)  LIKE ?
        """;

        String term = "%" + keyword.toLowerCase() + "%";
        return template.query(
                sql,
                new Object[]{ term, term, term },
                customerWithAddressMapper
        );
    }

    public void addAddress(Address address) {
        String sql = "INSERT INTO address (street, city, zip, country) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, address.getStreet());
            ps.setString(2, address.getCity());
            ps.setString(3, address.getZip());
            ps.setString(4, address.getCountry());
            return ps;
        }, keyHolder);
        address.setAddressId(keyHolder.getKey().intValue());
    }

    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customer (customer_name, email, phone_number, cpr_number, address_id, is_active) VALUES (?, ?, ?, ?, ?, ?)";
        template.update(sql,
                customer.getCustomerName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getCprNumber(),
                customer.getAddress().getAddressId(),
                customer.isActive());
    }

    public boolean deleteById(int customerId) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        int rowsDeleted = template.update(sql, customerId);
        return rowsDeleted > 0;
    }

    public Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        return template.queryForObject(sql, customerWithAddressMapper, customerId);
    }

    public Optional<Customer> findByEmail(String email) {
        String sql = "SELECT * FROM customer WHERE email = ?";
        try {
            Customer customer = template.queryForObject(sql, customerWithAddressMapper, email);
            return Optional.ofNullable(customer);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Customer getLastCreatedCustomer() {
        String sql = """
        SELECT
          c.customer_id,
          c.customer_name,
          c.email,
          c.phone_number,
          c.cpr_number,
          c.is_active,
          a.address_id,
          a.country,
          a.city,
          a.zip,
          a.street
        FROM customer c
        JOIN address a ON c.address_id = a.address_id
        ORDER BY c.created_date DESC
        LIMIT 1
        """;
        return template.queryForObject(sql, customerWithAddressMapper);
    }

}
