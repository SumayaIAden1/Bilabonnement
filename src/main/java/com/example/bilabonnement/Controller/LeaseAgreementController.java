package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.LeaseAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.bilabonnement.Service.Interface.LeaseAgreementServiceInterface;

import java.util.List;

@Controller
public class LeaseAgreementController
{

    @Autowired
    LeaseAgreementServiceInterface leaseAgreementService;

    //Startside: vis alle lejeaftaler
    @GetMapping("/leaseagreement")
    public String index(Model model)
    {
        List<LeaseAgreement> leaseAgreement = leaseAgreementService.fetchAll();
        model.addAttribute("leaseAgreements", new LeaseAgreement());
        return "leaseagreement/index"; // view: /templates/leaseagreement/index.html
    }

    @PostMapping("/leaseagreement/create")
    public String create(@ModelAttribute LeaseAgreement lease, Model model)
    {
        leaseAgreementService.addLeaseAgreement(lease);
        model.addAttribute("leaseAgreements", leaseAgreementService.fetchAll());
        model.addAttribute("leaseAgreement", new LeaseAgreement()); // ryd formular
        return "leaseagreement/index"; // samme side, men oversigten vises
    }

    //Vis formular til oprettelse
    @GetMapping("/leaseagreement/create")
    public String create(Model model)
    {
        model.addAttribute("leaseAgreement", new LeaseAgreement());
        return "leaseagreement/create"; // view: /templates/leaseagreement/create.html
    }


    //Gem ny aftale
    @PostMapping("/leaseagreements/createNew")
    public String createNew(@ModelAttribute LeaseAgreement leaseAgreement)
    {
        leaseAgreementService.addLeaseAgreement(leaseAgreement);
        return "redirect:/leaseagreements";
    }
}
