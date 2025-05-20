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
    public String dashboard(HttpSession session, Model model) //Session henter bruger der er logget ind, vi lægger data i model som thymeleaf kan bruge i HTML
    {
        User user = (User) session.getAttribute("user"); //vi henter brugeren fra sessionen, den blev sat når user loggede ind

        if (user == null || user.getUserRole() != User.UserRole.FORRETNINGSUDVIKLER && user.getUserRole() != User.UserRole.ADMIN) //sikrer at det kun er forretningsudviklere og admin der kan se dashboard (da man kan ændre html i browseren)
        {
            return "redirect:/";
        }

        int activeLeases = leaseAgreementService.getActiveLeaseCount();
        double totalPrice = leaseAgreementService.getTotaltPriceOfLeasedCars();

        //Her gør vi tallene klar til at blive vist i Thymeleaf
        model.addAttribute("activeLeases", activeLeases);
        model.addAttribute("totalPrice", totalPrice);

        return "business/dashboard";

    }

   
}
