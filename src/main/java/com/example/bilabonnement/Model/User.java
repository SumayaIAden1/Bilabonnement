package com.example.bilabonnement.Model;

import java.time.LocalDate;

public class User {

        private int userId;
        private String name;
        private String password;
        private String userRole;
        private boolean isActive;
        private LocalDate createdAt;

    public User() {
        // Tom constructor
    }

    // Constructor
        public User(int userId, String name, String password, String userRole, boolean isActive, LocalDate createdAt) {
            this.userId = userId;
            this.name = name;
            this.password = password;
            this.userRole = userRole;
            this.isActive = isActive;
            this.createdAt = createdAt;
        }

        // Getters and setters
        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUserRole() {
            return userRole;
        }

        public void setUserRole(String userRole) {
            this.userRole = userRole;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public LocalDate getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDate createdAt) {
            this.createdAt = createdAt;
        }


    }



