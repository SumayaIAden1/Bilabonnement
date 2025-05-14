package com.example.bilabonnement.Service;


import com.example.bilabonnement.Model.LeaseAgreement;
import com.example.bilabonnement.Repository.LeaseAgreementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LeaseAgreementService
{
    @Autowired
    LeaseAgreementRepo leaseAgreementRepo;
    public List<LeaseAgreement> fetchAll()
    {
        return leaseAgreementRepo.fetchAll();
    }

    /* Tilføjet Logik bag metoden*/
    public void addLeaseAgreement(LeaseAgreement leaseAgreement)
    {
        double total = calculateTotalPrice(leaseAgreement);
        leaseAgreement.setTotalPrice(total);

        leaseAgreementRepo.addLeaseAgreement(leaseAgreement);
    }


    public LeaseAgreement findLeaseAgreementById(int id)
    {
        return leaseAgreementRepo.findLeaseAgreementById(id);
    }

    public Boolean deleteLeaseAgreement(int id)
    {
        return leaseAgreementRepo.deleteLeaseAgreement(id);
    }

    public void updateLeaseAgreement(int id, LeaseAgreement leaseAgreement)
    {
        leaseAgreementRepo.updateLeaseAgreement(id, leaseAgreement);
    }

    /* Metode til beregning af pris --- ChronoUnit.DAYS.between(...): Beregner antal dage mellem to datoer.*/
    public double calculateTotalPrice(LeaseAgreement leaseAgreement) {
        LocalDate start = leaseAgreement.getStartDate().toLocalDate();
        LocalDate end = leaseAgreement.getEndDate().toLocalDate();

        long days = ChronoUnit.DAYS.between(start, end);

        if (days <= 0) {
            throw new IllegalArgumentException("Slutdato skal være efter startdato");
        }

        return Math.round((leaseAgreement.getMonthlyPrice() / 30.0) * days * 100.0) / 100.0;
    }



}
