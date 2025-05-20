package com.example.bilabonnement.Service.Interface;

import com.example.bilabonnement.Model.LeaseAgreement;
import java.util.List;


//Isabella
public interface LeaseAgreementServiceInterface {
    List<LeaseAgreement> fetchAll();
    void addLeaseAgreement(LeaseAgreement leaseAgreement);
    double calculateTotalPrice(LeaseAgreement leaseAgreement);
    int getActiveLeaseCount();
    double getTotaltPriceOfLeasedCars();
}
