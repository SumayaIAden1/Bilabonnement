package com.example.bilabonnement.Model;
import java.time.LocalDate;

public class Customer {

    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String cprNumber;
    private int addressId;
    private LocalDate createdAt;
    private boolean isActive;

    public Customer() {}

    // Constructor
    public Customer() {}

    public Customer(int customerId, String firstName, String lastName, String email,
                    String phoneNumber, String cprNumber, int addressId,
                    LocalDate createdAt, boolean isActive) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cprNumber = cprNumber;
        this.addressId = addressId;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }

    // Getters
    public int getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getCprNumber() { return cprNumber; }
    public int getAddressId() { return addressId; }
    public LocalDate getCreatedAt() { return createdAt; }
    public boolean isActive() { return isActive; }

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

