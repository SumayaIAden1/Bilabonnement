package com.example.bilabonnement.Service.Interface;

import com.example.bilabonnement.Model.LeaseAgreement;

import java.util.ArrayList;
import java.util.List;


//test dummy klasse så vi kan teste vores - Isabella
public class FakeLeaseAgreementService implements LeaseAgreementServiceInterface
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
