package com.example.bilabonnement.Model;


import java.time.LocalDate;

public class Sale {

    private int saleId;
    private LocalDate date;
    private double price;
    private String currency;
    private String customerName;
    private String saleAgreement;
    private int carId;
    private int leaseId;
    private boolean isPreSold;
    private String status;
    private LocalDate paymentDate;
    private String deliveryLocation;
    private String invoiceNumber;
    private String createdBy;

    public Sale(){}

    public Sale(int saleId, LocalDate date, double price, String currency, String customerName,
                String saleAgreement, int carId, int leaseId, boolean isPreSold, String status,
                LocalDate paymentDate, String deliveryLocation, String invoiceNumber, String createdBy) {
        this.saleId = saleId;
        this.date = date;
        this.price = price;
        this.currency = currency;
        this.customerName = customerName;
        this.saleAgreement = saleAgreement;
        this.carId = carId;
        this.leaseId = leaseId;
        this.isPreSold = isPreSold;
        this.status = status;
        this.paymentDate = paymentDate;
        this.deliveryLocation = deliveryLocation;
        this.invoiceNumber = invoiceNumber;
        this.createdBy = createdBy;
    }

    // Getter and Setter methods
    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSaleAgreement() {
        return saleAgreement;
    }

    public void setSaleAgreement(String saleAgreement) {
        this.saleAgreement = saleAgreement;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(int leaseId) {
        this.leaseId = leaseId;
    }

    public boolean isPreSold() {
        return isPreSold;
    }

    public void setPreSold(boolean preSold) {
        isPreSold = preSold;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    // Method to display sale details
    public void displaySaleDetails() {
        System.out.println("Sale ID: " + saleId);
        System.out.println("Sale Date: " + date);
        System.out.println("Price: " + price + " " + currency);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Sale Agreement: " + saleAgreement);
        System.out.println("Car ID: " + carId);
        System.out.println("Lease ID: " + leaseId);
        System.out.println("Pre-Sold: " + (isPreSold ? "Yes" : "No"));
        System.out.println("Status: " + status);
        System.out.println("Payment Date: " + paymentDate);
        System.out.println("Delivery Location: " + deliveryLocation);
        System.out.println("Invoice Number: " + invoiceNumber);
        System.out.println("Created By: " + createdBy);
    }
}




