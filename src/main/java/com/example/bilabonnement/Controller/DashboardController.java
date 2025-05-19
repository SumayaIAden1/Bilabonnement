package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.User;
import com.example.bilabonnement.Service.LeaseAgreementService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController
{
    @Autowired
    private LeaseAgreementService leaseAgreementService;

    @GetMapping("/business/dashboard")
    public String dashboard(HttpSession session, Model model)
    {
        User user = (User) session.getAttribute("user");

        if (user == null || user.getUserRole() != User.UserRole.FORRETNINGSUDVIKLER) //sikrer at det kun er forretningsudviklere der kan se dashboard (da man kan ændre html i browseren)
        {
            return "redirect:/";
        }

        int activeLeases = leaseAgreementService.getActiveLeaseCount();
        model.addAttribute("activeLeases", activeLeases);

        return "business/dashboard";
    }
}
