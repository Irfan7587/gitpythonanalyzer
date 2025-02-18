package Question2;
/*
 *  *  * Mohammed Irfan Battegeri
 *  *  * Object-oriented Software Development Assignment 2 Question 2
 *  *  * Implementing RentalOrder class
 *  *  * DePaul University
 */

public class RentalOrder {
    private double totalCost;
    private Customer customer;
    private Vehicle vehicle;
    private int rentalLength;

    // Constructor for a one-day rental
    public RentalOrder(Customer customer, Vehicle vehicle) {
        this(customer, vehicle, 1); //  default rentalLength = 1
    }

    // Constructor for a specified number of rental days
    public RentalOrder(Customer customer, Vehicle vehicle, int rentalLength) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalLength = rentalLength;
        this.totalCost = calculateTotalCost();
    }

    // Method to calculate the total cost based on vehicle rate, customer discount, and rental length
    public double calculateTotalCost() {
        double baseRate = vehicle.getDailyRate();
        double discount = customer.getDiscountRate();
        return rentalLength * baseRate * (1 - discount);
    }

    // Method to add the order to customer order history and mark the vehicle unavailable 
    public void completeOrder() {
        customer.addOrder(this);
        vehicle.setAvailable(false);
        System.out.println("Order completed: " + this);
    }

    // Getters and Setters for instance variables
    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getRentalLength() {
        return rentalLength;
    }

    public void setRentalLength(int rentalLength) {
        this.rentalLength = rentalLength;
    }

}
