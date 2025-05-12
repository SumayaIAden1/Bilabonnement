package com.example.bilabonnement.Service;


import com.example.bilabonnement.Model.LeaseAgreement;
import com.example.bilabonnement.Repository.LeaseAgreementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void addLeaseAgreement(LeaseAgreement leaseAgreement)
    {
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
}
