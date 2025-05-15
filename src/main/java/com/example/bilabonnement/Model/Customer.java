package com.example.bilabonnement.Model;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;


public class Customer {

    @NotNull(message = "Customer ID cannot be null")
    private int customerId;

    @NotNull(message = "Name cannot be null")
    @Size(min = 1, message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Phone number cannot be null")
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String phoneNumber;

    @NotNull(message = "CPR number cannot be null")
    private String cprNumber;

    @NotNull(message = "Address ID cannot be null")
    private int addressId;

    @NotNull(message = "Creation date cannot be null")
    private LocalDate createdAt;

    private boolean isActive;

    public Customer() {}

    // Constructor
    public Customer() {}

    // Constructor
    public Customer(int customerId, String name, String email, String phoneNumber,
                    String cprNumber, int addressId, LocalDate createdAt, boolean isActive) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cprNumber = cprNumber;
        this.addressId = addressId;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Setters
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setCprNumber(String cprNumber) { this.cprNumber = cprNumber; }
    public void setAddressId(int addressId) { this.addressId = addressId; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }
    public void setActive(boolean active) { isActive = active; }
}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCprNumber() {
        return cprNumber;
    }

    public void setCprNumber(String cprNumber) {
        this.cprNumber = cprNumber;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
