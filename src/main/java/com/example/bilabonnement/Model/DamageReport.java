package com.example.bilabonnement.Model;

import java.time.LocalDate;

public class DamageReport {
    private int reportId;
    private LocalDate date;
    private String description;
    private double price;
    private String inspection;
    private String registrationNumber;
    private boolean customerPresent;
    private String status;
    private String attachmentPath;

    // Tom konstruktør
    public DamageReport() {
    }

    // Konstruktør
    public DamageReport(int reportId, LocalDate date, String description, double price,
                        String inspection, boolean customerPresent, String status,
                        String registrationNumber, String attachmentPath) {
        this.reportId = reportId;
        this.date = date;
        this.description = description;
        this.price = price;
        this.inspection = inspection;
        this.customerPresent = customerPresent;
        this.status = status;
        this.registrationNumber = registrationNumber;
        this.attachmentPath = attachmentPath;
    }

    // Getters and setters
    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInspection() {
        return inspection;
    }

    public void setInspection(String inspection) {
        this.inspection = inspection;
    }

    public boolean isCustomerPresent() {
        return customerPresent;
    }

    public void setCustomerPresent(boolean customerPresent) {
        this.customerPresent = customerPresent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }
}

