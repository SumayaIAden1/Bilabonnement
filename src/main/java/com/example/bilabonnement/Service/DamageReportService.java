package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.Model.DamageReport;
import com.example.bilabonnement.Repository.CarRepo;
import com.example.bilabonnement.Repository.DamageReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageReportService {

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private DamageReportRepo damageReportRepo;

    // Returnerer en liste med alle skadesrapporter
    public List<DamageReport> findAll() {
        return damageReportRepo.findAll();
    }

    // Finder én skadesrapport baseret på rapportens ID
    public DamageReport findById(int id) {
        return damageReportRepo.findById(id);
    }

    // Finder alle skadesrapporter, der er knyttet til en bestemt bil
    public List<DamageReport> findByCarId(int carId) {
        return damageReportRepo.findByCarId(carId);
    }


    public void save(DamageReport report) {
        Car car = carRepo.findCarById(report.getCarId());

        if (!"returneret".equalsIgnoreCase(car.getStatus())) {
            throw new IllegalStateException("Skader kan kun registreres på returnerede biler.");
        }

        if (report.getPrice() < 0) {
            throw new IllegalArgumentException("Prisen må ikke være negativ.");
        }

        damageReportRepo.save(report);
    }


    // Opdaterer en eksisterende skadesrapport
    public void update(DamageReport report) {
        damageReportRepo.update(report);
    }

    // Sletter en skadesrapport ud fra dens ID
    public void deleteById(int id) {
        damageReportRepo.deleteById(id);
    }
}
