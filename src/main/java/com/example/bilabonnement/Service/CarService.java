package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.Repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepo carRepo;

    // Henter alle biler
    public List<Car> fetchAll() {
        return carRepo.fetchAll();
    }

    // Tilføjer ny bil
    public void addCar(Car car) {
        carRepo.addCar(car);
    }

    // Finder bil ud fra registreringsnummer
    public Car findCarByRegistration(String registrationNumber) {
        return carRepo.findCarByRegistration(registrationNumber);
    }

    // Sletter bil
    public boolean deleteCar(String registrationNumber) {
        return carRepo.deleteCar(registrationNumber);
    }

    // Opdaterer bil
    public void updateCar(String registrationNumber, Car car) {
        carRepo.updateCar(registrationNumber, car);
    }

    //------------------------------------------------------------------------------------------------------------------

    //US6

    public List<Car> fetchAllLeasedCars()
    {
        return carRepo.fetchAllLeasedCars();
    }

    //------------------------------------------------------------------------------------------------------------------
}
