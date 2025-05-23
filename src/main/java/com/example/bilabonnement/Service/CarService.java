package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.DTO.CarWithModelDTO;
import com.example.bilabonnement.Repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.coyote.ActionCode.AVAILABLE;

@Service
public class CarService {

    @Autowired
    private CarRepo carRepo;

    // Henter alle biler
    public List<Car> fetchAll() {
        return carRepo.fetchAll();
    }

    // Isabella - Hent alle biler med modelinfo (DTO)
    public List<CarWithModelDTO> fetchAllCarsWithModel() {
        return carRepo.fetchAllCarsWithModel();
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

    //Metode til dropdown (registreringsnumre)
    public List<String> getAllRegistrationNumbers() {
        return carRepo.fetchAllRegistrationNumbers();
    }

    //------------------------------------------------------------------------------------------------------------------

    //US6

    public List<Car> fetchAllLeasedCars()
    {
        return carRepo.fetchAllLeasedCars();
    }

    //------------------------------------------------------------------------------------------------------------------

    /*Isabella - Se status på biler i dashboard*/

    public Map<String, Integer> getCarStatusOverview() {
        return carRepo.getCarCountByStatus();
    }

    //Isabella - metode til at vise status og modelnavn på biler i databasen....----------------------------------------

    /*public List<Map<String , Object>> getCarCountByStatusAndModel()
    {
        return carRepo.getCarCountByStatusAndModel();
    }*/

    // Isabella - Henter status-fordeling pr. modelnavn til dashboard
    public Map<String, Map<String, Integer>> getStatusCountsGroupedByModel() {
        return carRepo.getStatusCountsGroupedByModel();
    }

    /*Isabella- find pris pr. måned-----------------------------------------------------------------------------------*/


    public List<Car> getAvailableCars() {
        List<Car> allCars = (List<Car>) carRepo.fetchAll();
        List<Car> availableCars = new ArrayList<>();

        for (Car car : allCars) {
            if (car.getStatus() == Car.CarStatus.Available) {
                availableCars.add(car);
            }
        }

        return availableCars;
    }

    public double findMonthlyPriceByRegistration(String registrationNumber) {
        return carRepo.findMonthlyPriceByRegistration(registrationNumber);
    }


}


