package com.example.bilabonnement.Controller;


import com.example.bilabonnement.Model.Customer;
import com.example.bilabonnement.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // Vis form for at oprette kunde
    @GetMapping("/customers/create")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/createCustomer"; // templates/customer/create.html
    }

    // Tilføj kunde
    @PostMapping("/customers/addCustomer")
    public String addCustomer(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {
        try {
            customerService.addCustomer(customer); // Attempt to add customer
        } catch (IllegalArgumentException e) {
            // Catch error and add it as a flash attribute
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/customers/create"; // Redirect to the create form with error
        }
        return "redirect:/customers"; // If success, redirect to customer list
    }

    // Vis specifik kunde
    @GetMapping("/customers/viewOne/{id}")
    public String getCustomerById(@PathVariable("id") int id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer/viewOne"; // templates/customer/viewOne.html
    }

    // Vis form for opdatering af kunde
    @GetMapping("/customers/updateOne/{id}")
    public String updateForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customer/updateOne"; // templates/customer/updateOne.html
    }

    // Opdater kunde om omdiriger
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
