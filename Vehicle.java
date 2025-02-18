package Question2;
/*
 *  *  * Mohammed Irfan Battegeri
 *  *  * Object-oriented Software Development Assignment 2 Question 2
 *  *  * Implementing Vehicle class
 *  *  * DePaul University
 */


public class Vehicle {
    
    // Declaration of instance variables
    private int vehicleID;
    private String make;
    private String model;
    private int year;
    private double mileage;
    private VehicleType carType;
    private boolean available;
    private double dailyRate;

    // Vehicle Constructor
    public Vehicle(int vehicleID, String make, String model, int year, double mileage, VehicleType carType, double dailyRate) {
        this.vehicleID = vehicleID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.carType = carType;
        this.available = true;
        this.dailyRate = dailyRate;
    }

    // Getters and setters for instance variables
    public int getVehicleID() {
        return vehicleID;
    }
    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public double getMileage() {
        return mileage;
    }
    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public VehicleType getCarType() {
        return carType;
    }
    public void setCarType(VehicleType carType) {
        this.carType = carType;
    }

    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getDailyRate() {
        return dailyRate;
    }
    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }


}