package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.LeaseAgreement;
import com.example.bilabonnement.Service.Interface.LeaseAgreementServiceInterface;

import java.util.ArrayList;
import java.util.List;


//test dummy klasse så vi kan teste vores metoder uden at bruge databasen - Isabella

public class MockLeaseAgreementService implements LeaseAgreementServiceInterface
{
    public List<LeaseAgreement> fetchAll()
    {
        return new ArrayList<>();
    }

    public void addLeaseAgreement(LeaseAgreement l) {}

    public double calculateTotalPrice(LeaseAgreement l)
    {
        return 1234.56;
    }

    public int getActiveLeaseCount()
    {
        return 2;
    }

    public double getTotaltPriceOfLeasedCars()
    {
        return 56789.0;
    }
}
