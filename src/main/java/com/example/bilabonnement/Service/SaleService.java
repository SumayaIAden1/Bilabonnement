package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Sale;
import com.example.bilabonnement.Repository.SaleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepo saleRepo;

    // Create a new sale
    public void createSale(Sale sale) {
        saleRepo.createSale(sale);
    }

    // Get all sales
    public List<Sale> getAllSales() {
        return saleRepo.getAllSales();
    }

    // Get a sale by its ID
    public Sale getSaleById(int id) {
        return saleRepo.getSaleById(id);
    }

    // Delete a sale by its ID
    public void deleteSale(int id) {
        saleRepo.deleteSale(id);
    }
}
