package com.example.bilabonnement.Service;


import com.example.bilabonnement.Model.Customer;
import com.example.bilabonnement.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    // Henter alle kunder
    public List<Customer> getAllCustomers() {
        return customerRepo.findAllWithAddress();
    }
    /*
    Når du annoterer metoden med @Transactional, sørger Spring for, at begge databaseoperationer —
    indsættelse af adresse og indsættelse af kunde — kører i én og samme transaktion.
    */
    @Transactional
    public void addCustomerWithAddress(Customer customer) {
        // Indsæt adressen først og få den genererede address_id
        customerRepo.addAddress(customer.getAddress());
        // Indsæt nu kunden med den gyldige address_id
        customerRepo.addCustomer(customer);
    }

    public List<Customer> searchCustomers(String keyword) {
        return customerRepo.searchWithAddress(keyword);
    }

    // Slet en kunde ud fra deres ID
    public boolean deleteById(int customerId) {
        return customerRepo.deleteById(customerId);
    }

    // Hent en kunde ud fra deres ID
    public Customer getCustomerById(int customerId) {
        return customerRepo.getCustomerById(customerId);
    }

    // Hent den sidst oprettede kunde
    public Customer getLastCreatedCustomer() {
        return customerRepo.getLastCreatedCustomer();  // Kalder repo-metoden
    }

}
