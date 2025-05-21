package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Address;
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
    @GetMapping("/customer")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customer/index"; // templates/customer/index.html
    }

    @GetMapping("/customer/create")
    public String create(Model model) {
        Customer customer = new Customer();
        customer.setAddress(new Address());
        model.addAttribute("customer", customer);
        return "redirect:/"; // templates/home/customer/createCustomer.html
    }

    @PostMapping("/customers/addCustomer")
    public String addCustomer(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {
        try {
            // Create and set the Address object
            Address address = new Address(0, customer.getAddress().getStreet(),
                    customer.getAddress().getCity(),
                    customer.getAddress().getZip(),
                    customer.getAddress().getCountry());

            // Add the customer and address to the database
            customerService.addCustomerWithAddress(customer, address);

            return "redirect:/customers/confirmation";  // Redirect to the confirmation page
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/customers/create";  // Redirect to the create form with error
        }
    }


    @GetMapping("/customers/confirmation")
    public String confirmation(Model model) {
        Customer lastCreatedCustomer = customerService.getLastCreatedCustomer();
        model.addAttribute("message", "Kunden blev oprettet succesfuldt!");
        model.addAttribute("customer", lastCreatedCustomer);
        return "home/customer/customerCreatedConfirmation";  // Return to the confirmation page
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
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);  // Add the customer to the model
        return "customer/updateOne"; // templates/customer/updateOne.html
    }

    // Slet en kunde
    @GetMapping("/customers/deleteOne/{id}")
    public String deleteById(@PathVariable("id") int id) {
        customerService.deleteById(id);
        return "redirect:/customers";
    }
}
