package Question2;
/*
 *  *  * Mohammed Irfan Battegeri
 *  *  * Object-oriented Software Development Assignment 2 Question 2
 *  *  * Implementing Customer abtract class
 *  *  * DePaul University
 */

import java.util.ArrayList;
import java.util.List;

public abstract class Customer implements Reportable {
    // Declaration of instance variables
    private int id;
    private String name;
    private double discountRate;
    private List<RentalOrder> rentalOrders;

    // Constructor for Customer abstract class
    public Customer(int id, String name, double discountRate) {
        this.id = id;
        this.name = name;
        this.discountRate = discountRate;
        this.rentalOrders = new ArrayList<>();
    }

    // Method to add a rental order
    public void addOrder(RentalOrder order) {
        rentalOrders.add(order);
        System.out.println("Rental order added successfully");
    }
    
    // Method to remove a order
    public void removeOrder(RentalOrder order) {
        rentalOrders.remove(order);
        System.out.println("Rental Order removed successfully");
    }

    public List<RentalOrder> getOrderHistory() {
        return new ArrayList<>(rentalOrders);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    @Override
    public abstract String generateReport();
}