package com.example.bilabonnement.Model;

public class Car
{
    private int carId;
    private String model;
    private String regNumber;
    private String status;
    private String brand;
    private String vinNumber;
    private String color;
    private String equipmentLevel;
    private double co2Emission;
    private double purchasePrice;
    private double regFee;
    private int mileage;
    private String location;

    public Car(){}

    public Car(int carId, String brand, String regNumber, String status, String vinNumber, String color, String equipmentLevel,
               double co2Emission, double purchasePrice, double regFee, int mileage, String location)
    {
        this.carId = carId;
        this.brand = brand;
        this.regNumber = regNumber;
        this.status = status;
        this.vinNumber = vinNumber;
        this.color = color;
        this.equipmentLevel = equipmentLevel;
        this.co2Emission = co2Emission;
        this.purchasePrice = purchasePrice;
        this.regFee = regFee;
        this.mileage = mileage;
        this.location = location;
    }

    public int getCarId()
    {
        return carId;
    }

    public String getModel()
    {
        return model;
    }

    public String getRegNumber()
    {
        return regNumber;
    }

    public String getStatus()
    {
        return status;
    }

    public String getBrand()
    {
        return brand;
    }

    public String getVinNumber()
    {
        return vinNumber;
    }

    public String getColor()
    {
        return color;
    }

    public String getEquipmentLevel()
    {
        return equipmentLevel;
    }

    public double getCo2Emission()
    {
        return co2Emission;
    }

    public double getPurchasePrice()
    {
        return purchasePrice;
    }

    public double getRegFee()
    {
        return regFee;
    }

    public int getMileage()
    {
        return mileage;
    }

    public String getLocation()
    {
        return location;
    }

    public void setCarId(int carId)
    {
        this.carId = carId;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public void setRegNumber(String regNumber)
    {
        this.regNumber = regNumber;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public void setVinNumber(String vinNumber)
    {
        this.vinNumber = vinNumber;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public void setEquipmentLevel(String equipmentLevel)
    {
        this.equipmentLevel = equipmentLevel;
    }

    public void setCo2Emission(double co2Emission)
    {
        this.co2Emission = co2Emission;
    }

    public void setPurchasePrice(double purchasePrice)
    {
        this.purchasePrice = purchasePrice;
    }

    public void setRegFee(double regFee)
    {
        this.regFee = regFee;
    }

    public void setMileage(int mileage)
    {
        this.mileage = mileage;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }
}
