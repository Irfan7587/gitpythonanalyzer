package Question2;
/*
 *  *  * Mohammed Irfan Battegeri
 *  *  * Object-oriented Software Development Assignment 2 Question 2
 *  *  * Implementing Inventory class
 *  *  * DePaul University
 */

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    // Declaration of instance variables
    private int inventoryID;
    private List<Vehicle> listOfVehicles;
    private VehicleType inventoryType;

    // Inventory Constructor
    public Inventory(int inventoryID, VehicleType car) {
        this.inventoryID = inventoryID;
        this.listOfVehicles = new ArrayList<Vehicle>();
        this.inventoryType = car;
    }


    // Method to add a vehicle to inventory list
    public void addVehicle(Vehicle vehicle) {   
        if(vehicle.getCarType().equals(this.inventoryType)) {
            listOfVehicles.add(vehicle);
            System.out.println("Vehicle added successfully to the inventory");
        } else {
            System.out.println("Vehicle type do not match the type of the inventory");
        }
    }
    
    // Method to remove a vehicle from the inventory list
    public void removeVehicle(Vehicle vehicle) {   
        listOfVehicles.remove(vehicle);
        System.out.println("Vehicle removed from the inventory");
    }

    // Getters and Setters for instance variables
    public int getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public List<Vehicle> getListOfVehicles() {
        return listOfVehicles;
    }

    public void setListOfVehicles(List<Vehicle> listOfVehicles) {
        this.listOfVehicles = listOfVehicles;
    }

    public VehicleType getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(VehicleType inventoryType) {
        this.inventoryType = inventoryType;
    }
}

