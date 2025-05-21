package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.LeaseAgreement;
import com.example.bilabonnement.Service.CarService;
import com.example.bilabonnement.Service.Interface.LeaseAgreementServiceInterface;
import com.example.bilabonnement.Service.LeaseAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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



    // Startside: vis alle lejeaftaler
    @GetMapping("")
    public String index(Model model, HttpSession session) {
        model.addAttribute("leaseAgreements", leaseAgreementService.fetchAll());
        model.addAttribute("user", session.getAttribute("user"));
        return "leaseagreement/index";
    }

    // Trin 1: vis første formular
    @GetMapping("/create-step1")
    public String showStep1(Model model) {
        model.addAttribute("leaseAgreement", new LeaseAgreement());
        model.addAttribute("registrationNumbers", carService.getAllRegistrationNumbers());
        return "leaseagreement/create-step1";
    }

    // Trin 1: modtag data og gå videre til step 2
    @PostMapping("/create-step1")
    public String processStep1(@ModelAttribute("leaseAgreement") LeaseAgreement leaseAgreement) {
        return "redirect:/leaseagreement/create-step2";
    }

    // Trin 2: vis næste formular
    @GetMapping("/create-step2")
    public String showStep2(@ModelAttribute("leaseAgreement") LeaseAgreement leaseAgreement, Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("customerList", customerService.getAllCustomers());
        //model.addAttribute("locationList", locationService.getAllLocations());
        return "leaseagreement/create-step2";
    }


    // Trin 2: modtag og gå videre til preview
    @PostMapping("/create-step2")
    public String processStep2(@ModelAttribute("leaseAgreement") LeaseAgreement leaseAgreement) {
        return "redirect:/leaseagreement/preview";
    }

    // Trin 3: vis preview før bekræftelse
    @GetMapping("/preview")
    public String showPreview(@ModelAttribute("leaseAgreement") LeaseAgreement leaseAgreement) {
        return "leaseagreement/preview";
    }

    // Trin 4: bekræft og gem i DB
    @PostMapping("/confirm")
    public String confirm(@ModelAttribute("leaseAgreement") LeaseAgreement leaseAgreement) {
        leaseAgreementService.addLeaseAgreement(leaseAgreement);
        return "redirect:/leaseagreement/confirm";
    }

    // Kvitteringsside efter gemt aftale
    @GetMapping("/confirm")
    public String confirmed() {
        return "leaseagreement/confirm";
    }
}
