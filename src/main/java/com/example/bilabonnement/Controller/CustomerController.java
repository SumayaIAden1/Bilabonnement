package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Address;
import com.example.bilabonnement.Model.Customer;
import com.example.bilabonnement.Model.User;
import com.example.bilabonnement.Service.CustomerService;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/customers/startpage")
    public String startpage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);
        return "customer/startpage";
    }

    @GetMapping("/customers/overview")
    public String getAllCustomers(
            @RequestParam(value = "keyword", required = false) String keyword,
            HttpSession session,
            Model model
    ) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);

        List<Customer> customers;
        if (keyword != null && !keyword.isBlank()) {
            customers = customerService.searchCustomers(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            customers = customerService.getAllCustomers();
        }
        model.addAttribute("customers", customers);
        return "customer/overview";
    }



    // Opret kunde metode
    @GetMapping("/customers/create")
    public String create(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);


        Customer customer = new Customer();
        customer.setAddress(new Address());
        model.addAttribute("customer", customer);

        return "customer/createCustomer";
    }


    // Tilføjer selve kunden til databasen efter oprettelse
    @PostMapping("/customers/addCustomer")
    public String addCustomer(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {
        try {
            customerService.addCustomerWithAddress(customer);
            return "redirect:/customers/confirmation";  // Redirect to the confirmation page
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/customers/create";  // Redirect to the create form with error
        }
    }

    // Bekræftelsessiden for oprettelse af kunde
    @GetMapping("/customers/confirmation")
    public String confirmation(Model model) {
        Customer lastCreatedCustomer = customerService.getLastCreatedCustomer();
        model.addAttribute("message", "Kunden blev oprettet succesfuldt!");
        model.addAttribute("customer", lastCreatedCustomer);
        return "customer/customerCreatedConfirmation";  // Return to the confirmation page
    }


    // Vis specifik kunde med id
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