package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.LeaseAgreement;
import com.example.bilabonnement.Service.LeaseAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LeaseAgreementController {

    @Autowired
    LeaseAgreementService leaseAgreementService;

    //Startside: vis alle lejeaftaler
    @GetMapping("/leaseagreements")
    public String index(Model model) {
        List<LeaseAgreement> leaseAgreements = leaseAgreementService.fetchAll();
        model.addAttribute("leaseAgreements", leaseAgreements);
        return "leaseagreement/index"; // view: /templates/leaseagreement/index.html
    }

    //Vis formular til oprettelse
    @GetMapping("/leaseagreements/create")
    public String create(Model model) {
        model.addAttribute("leaseAgreement", new LeaseAgreement());
        return "leaseagreement/create"; // view: /templates/leaseagreement/create.html
    }

    //Gem ny aftale
    @PostMapping("/leaseagreements/createNew")
    public String createNew(@ModelAttribute LeaseAgreement leaseAgreement) {
        leaseAgreementService.addLeaseAgreement(leaseAgreement);
        return "redirect:/leaseagreements";
    }

    //Vis én lejeaftale
    @GetMapping("/leaseagreements/viewOne/{id}")
    public String viewOne(@PathVariable("id") int id, Model model) {
        model.addAttribute("leaseAgreement", leaseAgreementService.findLeaseAgreementById(id));
        return "leaseagreement/viewOne"; // view: /templates/leaseagreement/viewOne.html
    }

    //Slet én lejeaftale
    @GetMapping("/leaseagreements/deleteOne/{id}")
    public String deleteOne(@PathVariable("id") int id) {
        boolean deleted = leaseAgreementService.deleteLeaseAgreement(id);
        return "redirect:/leaseagreements";
    }

    //Vis formular til opdatering
    @GetMapping("/leaseagreements/updateOne/{id}")
    public String updateOne(@PathVariable("id") int id, Model model) {
        model.addAttribute("leaseAgreement", leaseAgreementService.findLeaseAgreementById(id));
        return "leaseagreement/updateOne"; // view: /templates/leaseagreement/updateOne.html
    }

    //Gem opdateret aftale
    @PostMapping("/leaseagreements/updateAgreement")
    public String updateAgreement(@ModelAttribute LeaseAgreement leaseAgreement) {
        leaseAgreementService.updateLeaseAgreement(leaseAgreement.getLeaseId(), leaseAgreement);
        return "redirect:/leaseagreements";
    }
}
