package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.Repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService
{
    @Autowired
    CarRepo carRepo;
    public List<Car> fetchAll()
    {
        return carRepo.fetchAll();
    }

    public void addCar(Car car)
    {
        carRepo.addCar(car);
    }

    public Car findCarById(int id)
    {
        return carRepo.findCarById(id);
    }

    public Boolean deleteCar(int id)
    {
        return carRepo.deleteCar(id);
    }

    public void updateCar(int id, Car car)
    {
        carRepo.updateCar(id, car);
    }

    //------------------------------------------------------------------------------------------------------------------

    //US6

    public List<Car> fetchAllLeasedCars()
    {
        return carRepo.fetchAllLeasedCars();
    }

    //------------------------------------------------------------------------------------------------------------------
}
