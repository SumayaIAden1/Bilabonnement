package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.DamageReport;
import com.example.bilabonnement.Model.User;
import com.example.bilabonnement.Service.DamageReportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DamageReportController {

    @Autowired
    private DamageReportService damageReportService;

    // Vis alle skadesrapporter
    @GetMapping("/damages")
    public String index(Model model) {
        List<DamageReport> reports = damageReportService.findAll();
        model.addAttribute("damageReports", reports);
        return "damage/index"; // templates/damage/index.html
    }

    // Vis formular til oprettelse af ny skade
    @GetMapping("/damages/create")
    public String create(HttpSession session, Model model) {
        // top‐wrapper: check for logged‐in user
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);

        // original logic
        model.addAttribute("damageReport",
                new DamageReport(0, null, "", 0.0, "", false, "", null, "")
        );
        return "damage/create"; // templates/damage/create.html
    }


    // Gem ny skadesrapport
    @PostMapping("/damages/createNew")
    public String createNew(@ModelAttribute DamageReport damageReport) {
        damageReportService.save(damageReport);
        return "redirect:/damages";
    }

    // Vis en specifik skadesrapport
    @GetMapping("/damages/viewOne/{id}")
    public String viewOne(@PathVariable("id") int id, Model model) {
        model.addAttribute("damageReport", damageReportService.findById(id));
        return "damage/viewOne"; // templates/damage/viewOne.html
    }

    // Slet en skadesrapport
    @GetMapping("/damages/deleteOne/{id}")
    public String deleteOne(@PathVariable("id") int id) {
        damageReportService.deleteById(id);
        return "redirect:/damages";
    }

    // Vis formular til redigering
    @GetMapping("/damages/updateOne/{id}")
    public String updateOne(@PathVariable("id") int id, Model model) {
        model.addAttribute("damageReport", damageReportService.findById(id));
        return "damage/updateOne"; // templates/damage/updateOne.html
    }

    // Gem ændringer i skadesrapport
    @PostMapping("/damages/updateReport")
    public String updateReport(@ModelAttribute DamageReport damageReport) {
        damageReportService.update(damageReport);
        return "redirect:/damages";
    }
}
