package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.DamageReport;
import com.example.bilabonnement.Repository.DamageReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageReportService {

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


    // Gemmer en ny skadesrapport i databasen, med validering
    public void save(DamageReport report) {
        if (report.getDescription() == null || report.getDescription().isBlank()) {
            throw new IllegalArgumentException("Beskrivelse må ikke være tom.");
        }
        if (report.getPrice() < 0) {
            throw new IllegalArgumentException("Pris må ikke være negativ.");
        }
        if (report.getDate() == null) {
            throw new IllegalArgumentException("Dato skal angives.");
        }
        if (report.getCarId() <= 0) {
            throw new IllegalArgumentException("Bilens ID skal være positivt.");
        }

        // Når databasen er klar, virker dette


    // Opdaterer en eksisterende skadesrapport
    public void update(DamageReport report) {
        damageReportRepo.update(report);
    }

    // Sletter en skadesrapport ud fra dens ID
    public void deleteById(int id) {
        damageReportRepo.deleteById(id);
    }
}
