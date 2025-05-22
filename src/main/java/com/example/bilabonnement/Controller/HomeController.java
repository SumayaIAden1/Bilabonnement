package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Repository.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.bilabonnement.Model.User;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController
{

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String index()
    {
        return "home/index";
    }


    //Login controllers---------------------------------------------------------------------------------------------
    //denne metode tjekker brugeren og gemmer i sessionen hvis det er en bruger med adgang og er aktiv - Isabella
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,

                        HttpSession session,
                        Model model)
    {
        User user = userRepo.login(username, password);
        if(user != null && user.isActive())
        {
            session.setAttribute("user", user);
            return "redirect:/intranet";
        }
        else
        {
            model.addAttribute("error", "Forkert brugernavn eller kode");
            return "home/index";
        }
    }

    @GetMapping("/intranet")
    public String intranet(HttpSession session, Model model)
    {
        User user = (User) session.getAttribute("user");

        if(user == null)
        {
            return "redirect:/";
        }

        model.addAttribute("user", user);
        return "home/intranet";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    //--------------------------------------------------------------------------------------------------------------


    // midlertidig test af databaseforbindelse, man skal bruge http://localhost:9696/test-db for at teste forbindelsen - Isabella
    @GetMapping("/test-db")
    @ResponseBody
    public String testDb() {
        try {
            int antal = userRepo.getAllUsers().size();
            return "✅ Forbindelse virker! Antal brugere: " + antal;
        } catch (Exception e) {
            return "❌ Fejl ved databaseforbindelse: " + e.getMessage();
        }
    }





    /*@GetMapping("/leaseagreement")
    public String leaseagreement() {
        return "leaseagreement";
    }*/




}