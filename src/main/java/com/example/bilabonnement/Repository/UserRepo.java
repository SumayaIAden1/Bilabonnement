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
        String sql = "INSERT INTO user (username, user_password, user_role, is_active, created_at) " +
                "VALUES (?, ?, ?, ?, ?)";
        template.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getUserRole().name(), //for at få enum som en string skal vi bruge name() - Isabella
                user.isActive(),
                user.getCreatedAt());
    }

    // Get all users
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return template.query(sql, rowMapper);
    }

    // Get a user by their ID
    public User getUserById(int id) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    // Update a user's information
    public void updateUser(User user) {
        String sql = "UPDATE user SET username = ?, user_password = ?, user_role = ?, is_active = ?, created_at = ? WHERE user_id = ?";
        template.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getUserRole().name(), //for at få enum som en string skal vi bruge name() - Isabella,
                user.isActive(),
                user.getCreatedAt(),
                user.getUserId());
    }

    // Delete a user by their ID
    public void deleteUser(int id) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        template.update(sql, id);
    }

    // Find a user by their name
    public User findByName(String name) {
        String sql = "SELECT * FROM user WHERE username = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        try {
            return template.queryForObject(sql, rowMapper, name);
        } catch (Exception e) {
            return null; // Return null if no user is found with the given name
        }
    }

    // Validate login credentials (username and password)
    public boolean validateLogin(String username, String password) {
        String sql = "SELECT COUNT(*) FROM user WHERE username = ? AND user_password = ?";
        int count = template.queryForObject(sql, Integer.class, username, password);
        return count > 0; // Return true if user exists, false if not
    }

    // ✅ Login-metode med fuld bruger retur
    public User login(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND user_password = ?";
        try {
            return template.queryForObject(sql, (rs, rowNum) -> {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("username"));
                user.setPassword(rs.getString("user_password"));
                user.setUserRole(User.UserRole.valueOf(rs.getString("user_role")));
                user.setActive(rs.getBoolean("is_active"));
                user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate());
                return user;
            }, username, password);
        } catch (Exception e) {
            return null;
        }
    }
}






