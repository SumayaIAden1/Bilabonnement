package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Customer;
import com.example.bilabonnement.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    // Add a new customer
    public void addCustomer(Customer customer) {
        // Check if the email already exists in the database
        if (customerRepo.findByEmail(customer.getEmail()) != null) {
            throw new IllegalArgumentException("Email allerede i brug.");
        }
        customerRepo.addCustomer(customer);
    }


    // Update an existing customer
    public boolean updateCustomer(int customerId, Customer updatedCustomer) {
        return customerRepo.updateCustomer(customerId, updatedCustomer);
    }

    // Delete a customer by ID
    public boolean deleteById(int customerId) {
        return customerRepo.deleteById(customerId);
    }

    public Customer getCustomerById(int customerId) {
        return customerRepo.getCustomerById(customerId);
    }
}
