package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Sale;
import com.example.bilabonnement.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SaleController {

    @Autowired
    private SaleService saleService;

    //Sumaya - Opsat Sales Controller, men ikke nået i mål med at bruge alle funktioner, grundet mangel på tid

    // Vis alle salg
    @GetMapping("/sales")
    public String getAllSales(Model model) {
        List<Sale> sales = saleService.getAllSales();
        model.addAttribute("sales", sales);
        return "sale/index"; // templates/sale/index.html
    }

    // Vis formular til oprettelse af nyt salg
    @GetMapping("/sales/create")
    public String create(Model model) {
        model.addAttribute("sale", new Sale());
        return "sale/create"; // templates/sale/create.html
    }

    // Gem nyt salg
    @PostMapping("/sales/createSale")
    public String createSale(@ModelAttribute Sale sale) {
        saleService.createSale(sale);
        return "redirect:/sales";
    }

    // Vis specifikt salg
    @GetMapping("/sales/viewOne/{id}")
    public String getSaleById(@PathVariable("id") int id, Model model) {
        Sale sale = saleService.getSaleById(id);
        model.addAttribute("sale", sale);
        return "sale/viewOne"; // templates/sale/viewOne.html
    }

    // Slet et salg
    @GetMapping("/sales/deleteOne/{id}")
    public String deleteSale(@PathVariable("id") int id) {
        saleService.deleteSale(id);
        return "redirect:/sales";
    }
}
