package com.example.bilabonnement.Controller;

import com.example.bilabonnement.DTO.CarWithModelDTO;
import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.Service.CarModelService;
import com.example.bilabonnement.Service.CarService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarModelService carModelService;

    // Vis alle biler
    /*@GetMapping("/dataentry/cars")
    public String getAllCars(Model model) {
        List<Car> cars = carService.fetchAll(); // Isabella - Vi henter alle biler fra databasen
        model.addAttribute("cars", cars); // Listen bliver tilgængelig i html
        return "dataentry/cars";
    }*/

    //Isbella - alle biler med model
    @GetMapping("/dataentry/cars")
    public String getAllCars(Model model, HttpSession session) {
        List<CarWithModelDTO> cars = carService.fetchAllCarsWithModel();
        model.addAttribute("cars", cars);
        model.addAttribute("user", session.getAttribute("user")); // Skal være med så så brugeren er tilgængelig for Thymeleaf
        return "dataentry/cars";
    }

    @GetMapping("/cars/create")
    public String createCarForm(Model model, HttpSession session) {
        model.addAttribute("car", new Car());
        model.addAttribute("models", carModelService.fetchAll());
        model.addAttribute("user", session.getAttribute("user")); // Gør bruger tilgængelig
        return "dataentry/create";
    }

    @PostMapping("/cars/addCar")
    public String addCar(@ModelAttribute Car car, RedirectAttributes redirectAttributes) {
        carService.addCar(car); // Gem bilen

        // Tilføj en flash-attribut med succesbesked
        redirectAttributes.addFlashAttribute("successMessage", "Bilen blev oprettet!");

        // Omdirigér til oversigten
        return "redirect:/dataentry/cars";
    }


    // Vis én bil
    @GetMapping("/cars/viewOne/{registrationNumber}")
    public String findCarByRegistration(@PathVariable("registrationNumber") String registrationNumber, Model model) {
        model.addAttribute("car", carService.findCarByRegistration(registrationNumber));
        return "car/viewOne"; // templates/car/viewOne.html
    }

    // Isabella - opdater bilens status
    @GetMapping("/cars/updateOne/{registrationNumber}")
    public String updateForm(@PathVariable("registrationNumber")
                                 String registrationNumber,
                                 Model model,
                                 HttpSession session)
    {

        model.addAttribute("car", carService.findCarByRegistration(registrationNumber));
        model.addAttribute("statuses", Car.CarStatus.values());
        model.addAttribute("user", session.getAttribute("user"));
        return "car/updateOne";
    }

    @PostMapping("/cars/updateStatus")
    public String updateCarStatus(@RequestParam String registrationNumber,
                                  @RequestParam String status) {
        // Find den rigtige bil fra databasen
        Car car = carService.findCarByRegistration(registrationNumber);

        // Sæt status korrekt
        car.setStatus(Car.CarStatus.valueOf(status));

        // Opdater bilen i databasen
        carService.updateCar(car.getRegistrationNumber(), car);

        // Gå tilbage til biloversigten
        return "redirect:/dataentry/cars";
    }




    // Slet bil
    @GetMapping("/cars/deleteOne/{registrationNumber}")
    public String deleteCar(@PathVariable("registrationNumber") String registrationNumber) {
        carService.deleteCar(registrationNumber);
        return "redirect:/cars";
    }
}
