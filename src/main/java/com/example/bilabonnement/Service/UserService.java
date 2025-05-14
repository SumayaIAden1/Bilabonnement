package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.User;
import com.example.bilabonnement.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    // Create a new user
    public void saveUser(User user) {
        userRepo.createUser(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    // Get a user by their ID
    public User getUserById(int id) {
        return userRepo.getUserById(id);
    }

    // Update a user's information
    public void updateUser(User user) {
        userRepo.updateUser(user);
    }

    // Delete a user by ID
    public void deleteUser(int id) {
        userRepo.deleteUser(id);
    }

    // Find a user by their name
    public User findByName(String name) {
        return userRepo.findByName(name);
    }

    // Validate login credentials (username and password)
    public boolean validateLogin(String username, String password) {
        return userRepo.validateLogin(username, password);
    }
}
