package com.example.bilabonnement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Vis alle kunder
    @GetMapping("/customers")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customer/index"; // templates/customer/index.html
    }

    // Vis formular til oprettelse af ny kunde
    @GetMapping("/customers/create")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/create"; // templates/customer/create.html
    }

    // Gem ny kunde
    @PostMapping("/customers/addCustomer")
    public String addCustomer(@ModelAttribute Customer customer) {
        customerService.addCustomer(customer);
        return "redirect:/customers";
    }

    // Vis en specifik kunde
    @GetMapping("/customers/viewOne/{id}")
    public String getCustomerById(@PathVariable("id") int id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer/viewOne"; // templates/customer/viewOne.html
    }

    // Vis formular til opdatering
    @GetMapping("/customers/updateOne/{id}")
    public String updateForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customer/updateOne"; // templates/customer/updateOne.html
    }

    // Gem ændringer
    @PostMapping("/customers/updateCustomer")
    public String updateCustomer(@ModelAttribute Customer customer) {
        customerService.updateCustomer(customer.getCustomerId(), customer);
        return "redirect:/customers";
    }

    // Slet en kunde
    @GetMapping("/customers/deleteOne/{id}")
    public String deleteById(@PathVariable("id") int id) {
        customerService.deleteById(id);
        return "redirect:/customers";
    }
}
