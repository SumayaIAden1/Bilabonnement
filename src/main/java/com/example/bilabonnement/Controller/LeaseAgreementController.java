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

// Gemmer "leaseAgreement" i sessionen, så det huskes mellem trin 1, 2 og 3 i oprettelsesprocessen.
// Det betyder, at brugerens data ikke forsvinder mellem side-skift.
@SessionAttributes("leaseAgreement")

public class LeaseAgreementController
{

    @Autowired
    LeaseAgreementServiceInterface leaseAgreementService;

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

    //Opretter trinvis oprettelse af lejeaftale
    @GetMapping("/create/step1")
    public String showStep1(Model model){
        model.addAttribute("leaseAgreement", new LeaseAgreement());
        model.addAttribute("registrationNumbers", carService.getAllRegistrationNumbers());
        return "leaseagreement/create-step1";
    }

    //Redirecter dig videre til step 2 i processen
    @PostMapping("/create/step1")
    public String processStep1(@ModelAttribute("leaseAgreement") LeaseAgreement leaseAgreeement){
        return "redirect:/leaseagreement/create-step2";
    }

    //Opretter trin 2 til oprettelse af lejeaftale
    @GetMapping("/create-step2")
    public String showStep2(@ModelAttribute("leaseAgreement") LeaseAgreement leaseAgreement){
        return"leaseagreement/create-step2";
    }

    @PostMapping("/create-step2")
    public String processStep2(@ModelAttribute("leaseAgreement") LeaseAgreement leaseAgreement){
        return"redirect:/leaseagreement/preview";
    }

    // Trin 3: Gennemse og bekræft oplysningerne før oprettelse
    @GetMapping("/preview")
    public String showPreview(@ModelAttribute("leaseAgreement") LeaseAgreement leaseAgreement) {
        return "leaseagreement/preview";
    }

    @GetMapping("/confirm")
    public String confirmed() {
        return "leaseagreement/confirm";
    }

    // Trin 4: Når brugeren trykker “Bekræft”, gemmes lejeaftalen i databasen
    @PostMapping("/confirm")
    public String confirm(@ModelAttribute("leaseAgreement") LeaseAgreement leaseAgreement) {
        leaseAgreementService.addLeaseAgreement(leaseAgreement);
        return "redirect:/leaseagreement/confirm";
    }


}
