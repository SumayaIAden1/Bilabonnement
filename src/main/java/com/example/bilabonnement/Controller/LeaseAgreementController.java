package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.LeaseAgreement;
import com.example.bilabonnement.Service.LeaseAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/leaseagreements")
public class LeaseAgreementController {

    @Autowired
    private LeaseAgreementService leaseAgreementService;

    // ➕ Vis form til oprettelse
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("leaseAgreement", new LeaseAgreement());
        return "leaseagreement/create";
    }

    // 💾 Gem ny lejeaftale
    @PostMapping("/create")
    public String addLeaseAgreement(@ModelAttribute LeaseAgreement leaseAgreement) {
        leaseAgreementService.addLeaseAgreement(leaseAgreement);
        return "redirect:/leaseagreements";
    }

    // 📋 Vis alle lejeaftaler
    @GetMapping
    public String listAll(Model model) {
        List<LeaseAgreement> agreements = leaseAgreementService.fetchAll();
        model.addAttribute("leaseAgreements", agreements);
        return "leaseagreement/index"; // Din Thymeleaf-visning
    }

    // ✏️ Rediger-formular
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        LeaseAgreement leaseAgreement = leaseAgreementService.findLeaseAgreementById(id);
        model.addAttribute("leaseAgreement", leaseAgreement);
        return "leaseagreement/edit";
    }

    // ✅ Gem ændringer
    @PostMapping("/update/{id}")
    public String updateLeaseAgreement(@PathVariable("id") int id, @ModelAttribute LeaseAgreement leaseAgreement) {
        leaseAgreementService.updateLeaseAgreement(id, leaseAgreement);
        return "redirect:/leaseagreements";
    }

    // 🗑 Slet lejeaftale
    @GetMapping("/delete/{id}")
    public String deleteLeaseAgreement(@PathVariable("id") int id) {
        leaseAgreementService.deleteLeaseAgreement(id);
        return "redirect:/leaseagreements";
    }
}
