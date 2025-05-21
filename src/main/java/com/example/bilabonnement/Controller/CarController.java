package com.example.bilabonnement.Controller;

import com.example.bilabonnement.DTO.CarWithModelDTO;
import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    // Vis alle biler
    /*@GetMapping("/dataentry/cars")
    public String getAllCars(Model model) {
        List<Car> cars = carService.fetchAll(); // Isabella - Vi henter alle biler fra databasen
        model.addAttribute("cars", cars); // Listen bliver tilgængelig i html
        return "dataentry/cars";
    }*/

    //Isbella - alle biler med model
    @GetMapping("/dataentry/cars")
    public String getAllCars(Model model) {
        List<CarWithModelDTO> cars = carService.fetchAllCarsWithModel(); // DTO med car + model
        model.addAttribute("cars", cars);
        return "dataentry/cars";
    }

    // Vis formular til oprettelse af bil
    @GetMapping("/cars/create")
    public String create(Model model) {
        model.addAttribute("car", new Car());
        return "car/create"; // templates/car/create.html
    }

    // Gem ny bil
    @PostMapping("/cars/addCar")
    public String addCar(@ModelAttribute Car car) {
        carService.addCar(car);
        return "redirect:/cars";
    }

    // Vis én bil
    @GetMapping("/cars/viewOne/{registrationNumber}")
    public String findCarByRegistration(@PathVariable("registrationNumber") String registrationNumber, Model model) {
        model.addAttribute("car", carService.findCarByRegistration(registrationNumber));
        return "car/viewOne"; // templates/car/viewOne.html
    }

    // Vis formular til opdatering
    @GetMapping("/cars/updateOne/{registrationNumber}")
    public String updateForm(@PathVariable("registrationNumber") String registrationNumber, Model model) {
        model.addAttribute("car", carService.findCarByRegistration(registrationNumber));
        return "car/updateOne"; // templates/car/updateOne.html
    }

    // Gem ændringer
    @PostMapping("/cars/updateCar")
    public String updateCar(@ModelAttribute Car car) {
        carService.updateCar(car.getRegistrationNumber(), car);
        return "redirect:/cars";
    }

    // Slet bil
    @GetMapping("/cars/deleteOne/{registrationNumber}")
    public String deleteCar(@PathVariable("registrationNumber") String registrationNumber) {
        carService.deleteCar(registrationNumber);
        return "redirect:/cars";
    }
}
