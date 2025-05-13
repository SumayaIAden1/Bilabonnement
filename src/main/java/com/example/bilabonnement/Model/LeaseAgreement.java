package com.example.bilabonnement.Model;

import java.sql.Date;

public class LeaseAgreement
{
    private int leaseId;
    private Date startDate;
    private Date endDate;
    private double monthlyPrice;
    private double totalPrice;
    private String leaseType;
    private String status;
    private int carId;
    private int userId;
    private int customerId;
    private int locationId;

    public LeaseAgreement(){}

    public LeaseAgreement(int leaseId, Date startDate, Date endDate, double monthlyPrice, double totalPrice,
                          String leaseType, int carId, int customerId, int locationId)
    {
        this.leaseId = leaseId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.monthlyPrice = monthlyPrice;
        this.totalPrice = totalPrice;
        this.leaseType = leaseType;
        this.carId = carId;
        this.customerId = customerId;
        this.locationId = locationId;
    }

    public int getLeaseId()
    {
        return leaseId;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public double getMonthlyPrice()
    {
        return monthlyPrice;
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public String getLeaseType()
    {
        return leaseType;
    }

    public String getStatus()
    {
        return status;
    }

    public int getCarId()
    {
        return carId;
    }

    public int getCustomerId()
    {
        return customerId;
    }

    public int getLocationId()
    {
        return locationId;
    }

    public void setLeaseId(int leaseId)
    {
        this.leaseId = leaseId;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public void setMonthlyPrice(double monthlyPrice)
    {
        this.monthlyPrice = monthlyPrice;
    }

    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public void setLeaseType(String leaseType)
    {
        this.leaseType = leaseType;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setCarId(int carId)
    {
        this.carId = carId;
    }

    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }

    public void setLocationId(int locationId)
    {
        this.locationId = locationId;
    }
}
