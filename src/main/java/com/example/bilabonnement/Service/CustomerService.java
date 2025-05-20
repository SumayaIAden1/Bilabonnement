package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Address;
import com.example.bilabonnement.Model.Customer;
import com.example.bilabonnement.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    // Fetch all customers
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

//    public void addCustomerWithAddress(Customer customer, Address address) {
//        // First insert the address
//        customerRepo.addAddress(address);  // Assuming you have a repository to handle Address
//
//        // Now associate the customer with the created address
//        customer.setAddress(address);  // Set the address object
//        customerRepo.addCustomer(customer);
//    }

    public void addCustomerWithAddress(Customer customer, Address address) {
        // Insert the address first and get the generated address_id
        customerRepo.addAddress(address);

        // Now set the generated address_id in the customer object
        customer.setAddress(address); // The address object now has the correct address_id

        // Now insert the customer with the valid address_id
        customerRepo.addCustomer(customer);
    }

    // Delete a customer by their ID
    public boolean deleteById(int customerId) {
        return customerRepo.deleteById(customerId);
    }

    // Get a customer by their ID
    public Customer getCustomerById(int customerId) {
        return customerRepo.getCustomerById(customerId);
    }

    // Get the last created customer
    public Customer getLastCreatedCustomer() {
        return customerRepo.getLastCreatedCustomer();  // Call the repo method
    }




}

