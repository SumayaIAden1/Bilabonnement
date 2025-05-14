package com.example.bilabonnement.Controller;

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
    @GetMapping("/cars")
    public String getAllCars(Model model) {
        List<Car> cars = carService.fetchAll();
        model.addAttribute("cars", cars);
        return "car/index"; // templates/car/index.html
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
    @GetMapping("/cars/viewOne/{id}")
    public String findCarById(@PathVariable("id") int id, Model model) {
        model.addAttribute("car", carService.findCarById(id));
        return "car/viewOne"; // templates/car/viewOne.html
    }

    // Vis formular til opdatering
    @GetMapping("/cars/updateOne/{id}")
    public String updateForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("car", carService.findCarById(id));
        return "car/updateOne"; // templates/car/updateOne.html
    }

    // Gem ændringer
    @PostMapping("/cars/updateCar")
    public String updateCar(@ModelAttribute Car car) {
        carService.updateCar(car.getCarId(), car);
        return "redirect:/cars";
    }

    // Slet bil
    @GetMapping("/cars/deleteOne/{id}")
    public String deleteCarById(@PathVariable("id") int id) {
        carService.deleteCar(id);
        return "redirect:/cars";
    }
}
