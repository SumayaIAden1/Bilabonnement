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

    // JdbcTemplate bruges til at udføre SQL-forespørgsler mod databasen
    private final JdbcTemplate template;

    // RowMapper konverterer hver database-række til et Customer-objekt
    private final RowMapper<Customer> customerRowMapper = new BeanPropertyRowMapper<>(Customer.class);

    /*
     * Constructor, Spring injicerer JdbcTemplate automatisk.
     */
    @Autowired
    public CustomerRepo(JdbcTemplate template) {
        this.template = template;
    }

    /*
     * Henter alle kunder fra databasen.
     *
     * @return Liste af alle Customer-objekter
     */
//    public List<Customer> findAll() {
//        String sql = "SELECT * FROM customer";
//        return template.query(sql, customerRowMapper);
//    }

    /**
     * Fetch ALL customers together with their Address in one go.
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
          a.address_id   AS addr_id,
          a.country      AS addr_country,
          a.city         AS addr_city,
          a.zip          AS addr_zip,
          a.street       AS addr_street
        FROM customer c
        JOIN address a
          ON c.address_id = a.address_id
            """;

        return template.query(sql, new RowMapper<>() {
            @Override
            public Customer mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                Address addr = new Address();
                addr.setAddressId(rs.getInt("addr_id"));
                addr.setCountry(rs.getString("addr_country"));
                addr.setCity(rs.getString("addr_city"));
                addr.setZip(rs.getString("addr_zip"));
                addr.setStreet(rs.getString("addr_street"));

                Customer cust = new Customer();
                cust.setCustomerId(rs.getInt("customer_id"));
                cust.setCustomerName(rs.getString("customer_name"));
                cust.setEmail(rs.getString("email"));
                cust.setPhoneNumber(rs.getString("phone_number"));
                cust.setCprNumber(rs.getString("cpr_number"));
                cust.setActive(rs.getBoolean("is_active"));
                cust.setAddress(addr);

                return cust;
            }
        });
    }

    /*
     * Indsætter en ny adresse i 'address'-tabellen og opdaterer Address-objektet
     * med den automatisk genererede address_id.
     *
     * @param address Adresse-objekt med street, city, zip og country
     */
    public void addAddress(Address address) {
        String sql = "INSERT INTO address (street, city, zip, country) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Udfør INSERT og hent den genererede nøgle (address_id)
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, address.getStreet());
            ps.setString(2, address.getCity());
            ps.setString(3, address.getZip());
            ps.setString(4, address.getCountry());
            return ps;
        }, keyHolder);

        // Sæt den nye address_id tilbage på Address-objektet
        address.setAddressId(keyHolder.getKey().intValue());
    }

    /*
     * Opretter en ny kunde i 'customer'-tabellen.
     * Forudsætter at Address allerede er indsat, så address.getAddressId() er sat.
     *
     * @param customer Customer-objekt med navn, email, telefon, CPR, adresse og aktiv-status
     */
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

    /*
     * Sletter en kunde baseret på customer_id.
     *
     * @param customerId ID på kunden der skal slettes
     * @return true hvis mindst én række blev slettet, ellers false
     */
    public boolean deleteById(int customerId) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        int rowsDeleted = template.update(sql, customerId);
        return rowsDeleted > 0;
    }

    /*
     * Henter én kunde baseret på customer_id.
     *
     * @param customerId ID på den ønskede kunde
     * @return Customer-objekt (kaster exception hvis ikke fundet)
     */
    public Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        return template.queryForObject(sql, customerRowMapper, customerId);
    }

    /*
     * Søger efter en kunde på e-mailadresse.
     *
     * @param email Kundens e-mail
     * @return Optional med Customer hvis fundet, ellers Optional.empty()
     */
    public Optional<Customer> findByEmail(String email) {
        String sql = "SELECT * FROM customer WHERE email = ?";
        try {
            Customer customer = template.queryForObject(sql, customerRowMapper, email);
            return Optional.ofNullable(customer);
        } catch (EmptyResultDataAccessException e) {
            // Returner tom Optional hvis ingen række matcher
            return Optional.empty();
        }
    }

    /*
     * Henter den sidst oprettede kunde (baseret på created_date).
     *
     * @return Customer-objekt for den nyeste kunde
     */
    public Customer getLastCreatedCustomer() {
        String sql = "SELECT * FROM customer ORDER BY created_date DESC LIMIT 1";
        return template.queryForObject(sql, customerRowMapper);
    }
}
