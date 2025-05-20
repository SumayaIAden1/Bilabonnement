package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.LeaseAgreement;
import com.example.bilabonnement.Service.CarService;
import com.example.bilabonnement.Service.LeaseAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class LeaseAgreementController
{

    @Autowired
    LeaseAgreementService leaseAgreementService;

    @Autowired
    CarService carService;

    //Startside: vis alle lejeaftaler
    @GetMapping("/leaseagreement")
    public String index(Model model) {
        model.addAttribute("leaseAgreements", leaseAgreementService.fetchAll());
        return "leaseagreement/index";
    }

    //Vis formular til oprettelse
    @GetMapping("/leaseagreement/create")
    public String create(Model model) {
        model.addAttribute("leaseAgreement", new LeaseAgreement());
        model.addAttribute("registrationNumbers", carService.getAllRegistrationNumbers()); // dropdown data med gyldige biler
        return "leaseagreement/create";
    }

    //Gemmer og redirecter tilbage til leaseagreement siden
    @PostMapping("/leaseagreement/create")
    public String create(@ModelAttribute LeaseAgreement leaseAgreement) {
        leaseAgreementService.addLeaseAgreement(leaseAgreement);
        return "redirect:/leaseagreement";
    }
}
