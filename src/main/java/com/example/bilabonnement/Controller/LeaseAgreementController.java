package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.LeaseAgreement;
import com.example.bilabonnement.Service.CarService;
import com.example.bilabonnement.Service.CustomerService;
import com.example.bilabonnement.Service.Interface.LeaseAgreementServiceInterface;
import com.example.bilabonnement.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/leaseagreement")
@SessionAttributes("leaseAgreement")
public class LeaseAgreementController {

    @Autowired
    private LeaseAgreementServiceInterface leaseAgreementService;

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public String index(Model model, HttpSession session) {
            model.addAttribute("leaseAgreements", leaseAgreementService.fetchAll());
            model.addAttribute("leaseAgreement", new LeaseAgreement());
            model.addAttribute("cars", carService.getAvailableCars());
            model.addAttribute("user", session.getAttribute("user"));

        //Isabella - Hvis der ikke allerede er sat et aktivt faneblad, sætter vi det til "information" som standard
        if (!model.containsAttribute("activeTab")) {
            model.addAttribute("activeTab", "information");
        }
        // hvis du bruger dropdowns senere:
        // model.addAttribute("registrationNumbers", carService.getAllRegistrationNumbers());
        // model.addAttribute("userList", userService.getAllUsers());
        // model.addAttribute("customerList", customerService.getAllCustomers());

        return "leaseagreement/index";
    }


    @PostMapping("/create")
    public String create(@ModelAttribute("leaseAgreement") LeaseAgreement leaseAgreement) {
        leaseAgreementService.addLeaseAgreement(leaseAgreement);

        // Hvis lejeaftalen er aktiv → opdater bilen til Rented
        if (leaseAgreement.getStatus().equalsIgnoreCase("Active")) {
            carService.updateCarStatus(leaseAgreement.getCarRegistrationNumber(), "Rented");

        }
        return "redirect:/leaseagreement";
    }

    @PostMapping("/monthly-price")
    public String getMonthlyPrice(@ModelAttribute("leaseAgreement") LeaseAgreement leaseAgreement,
                                  Model model, HttpSession session) {
        double price = carService.findMonthlyPriceByRegistration(leaseAgreement.getCarRegistrationNumber());
        leaseAgreement.setMonthlyPrice(price);

        model.addAttribute("leaseAgreement", leaseAgreement);
        model.addAttribute("leaseAgreements", leaseAgreementService.fetchAll());
        model.addAttribute("cars", carService.getAvailableCars());
        model.addAttribute("user", session.getAttribute("user"));

        // Isabella - Sætter fanebladet, så brugeren forbliver på "Opret lejeaftale"
        model.addAttribute("activeTab", "LAformular");

        return "leaseagreement/index";
    }


    @GetMapping("/delete/{id}")
    public String deleteLease(@PathVariable int id) {
        leaseAgreementService.deleteLeaseAgreement(id);
        return "redirect:/leaseagreement";
    }

    @GetMapping("/edit/{id}")
    public String editLease(@PathVariable int id, Model model,HttpSession session) {
        LeaseAgreement leaseAgreement = leaseAgreementService.findById(id);
        model.addAttribute("leaseAgreement", leaseAgreement);
        model.addAttribute("user", session.getAttribute("user"));
        return "leaseagreement/edit";
    }

    @PostMapping("/update/{id}")
    public String updateLease(@PathVariable int id, @ModelAttribute LeaseAgreement leaseAgreement) {
        leaseAgreementService.updateLeaseAgreement(id, leaseAgreement);

        if (leaseAgreement.getStatus().equalsIgnoreCase("Completed") ||
                leaseAgreement.getStatus().equalsIgnoreCase("Cancelled")) {
            carService.updateCarStatus(leaseAgreement.getCarRegistrationNumber(), "Available");
        }
        return "redirect:/leaseagreement";
    }



}
