package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class UserRepo {

    @Autowired
    private JdbcTemplate template;



    // Create a new user
    public void createUser(User user) {
        String sql = "INSERT INTO users (name, password, userRole, isActive, createdAt) " +
                "VALUES (?, ?, ?, ?, ?)";
        template.update(sql, user.getName(), user.getPassword(), user.getUserRole(),
                user.isActive(), user.getCreatedAt());
    }

    // Get all users
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return template.query(sql, rowMapper);
    }

    // Get a user by their ID
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE userId = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    // Update a user's information
    public void updateUser(User user) {
        String sql = "UPDATE users SET name = ?, password = ?, userRole = ?, isActive = ?, createdAt = ? WHERE userId = ?";
        template.update(sql, user.getName(), user.getPassword(), user.getUserRole(),
                user.isActive(), user.getCreatedAt(), user.getUserId());
    }

    // Delete a user by their ID
    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE userId = ?";
        template.update(sql, id);
    }

    // Find a user by their name
    public User findByName(String name) {
        String sql = "SELECT * FROM users WHERE name = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        try {
            return template.queryForObject(sql, rowMapper, name);
        } catch (Exception e) {
            return null; // Return null if no user is found with the given name
        }
    }

    // Validate login credentials (username and password)
    public boolean validateLogin(String username, String password) {
        String sql = "SELECT COUNT(*) FROM users WHERE name = ? AND password = ?";
        int count = template.queryForObject(sql, Integer.class, username, password);
        return count > 0; // Return true if user exists, false if not
    }
}






