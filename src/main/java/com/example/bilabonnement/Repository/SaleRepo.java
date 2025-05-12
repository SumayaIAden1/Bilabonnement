package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class SaleRepo {

    @Autowired
    private JdbcTemplate template;

    // Create a new sale
    public void createSale(Sale sale) {
        String sql = "INSERT INTO sales (date, price, currency, customerName, saleAgreement, carId, leaseId, isPreSold, status, paymentDate, deliveryLocation, invoiceNumber, createdBy) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, sale.getDate(), sale.getPrice(), sale.getCurrency(), sale.getCustomerName(),
                sale.getSaleAgreement(), sale.getCarId(), sale.getLeaseId(), sale.isPreSold(), sale.getStatus(),
                sale.getPaymentDate(), sale.getDeliveryLocation(), sale.getInvoiceNumber(), sale.getCreatedBy());
    }

    // Get all sales
    public List<Sale> getAllSales() {
        String sql = "SELECT * FROM sales";
        RowMapper<Sale> rowMapper = new BeanPropertyRowMapper<>(Sale.class);
        return template.query(sql, rowMapper);
    }

    // Get a sale by its ID
    public Sale getSaleById(int id) {
        String sql = "SELECT * FROM sales WHERE saleId = ?";
        RowMapper<Sale> rowMapper = new BeanPropertyRowMapper<>(Sale.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    // Delete a sale by its ID
    public void deleteSale(int id) {
        String sql = "DELETE FROM sales WHERE saleId = ?";
        template.update(sql, id);
    }
}
