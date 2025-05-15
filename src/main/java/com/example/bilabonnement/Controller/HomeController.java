package com.example.bilabonnement.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{

    @GetMapping("/")
    public String index()
    {
        return "home/index"; // henviser til templates/index.html
    }


    @GetMapping("/intranet")
    public String intranet() {
        return "home/intranet";
    }
}