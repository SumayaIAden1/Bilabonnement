package com.example.bilabonnement.Model;

import java.time.LocalDate;

public class User {

        private int userId;
        private String name;
        private String password;
        private UserRole userRole;
        private boolean isActive;
        private LocalDate createdAt;


        //Tilføjet enum så de kan få forskellig adgang til funktioner på deres intranet
        public enum UserRole{
            ADMIN,
            SKADESMEDARBEJDE,
            DATAREGISTRERING,
            FORRETNINGSUDVIKLER
        }

    public User() {
        // Tom constructor
    }

    // Constructor
        public User(int userId, String name, String password, UserRole userRole, boolean isActive, LocalDate createdAt) {
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

        public UserRole getRole() {
            return userRole;
        }

        public void setUserRole(UserRole userRole) {
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



