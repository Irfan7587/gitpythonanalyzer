package Question2;
/*
 *  *  * Mohammed Irfan Battegeri
 *  *  * Object-oriented Software Development Assignment 2 Question 2
 *  *  * Implementing Corporate class
 *  *  * DePaul University
 */

public class Corporate extends Customer {
    // Declaration of instance variables
    private String country;
    private String taxID;

    // Constructor for Corporate class
    public Corporate(int id, String name, double discountRate, String country, String taxID) {
        super(id, name, discountRate);
        this.country = country;
        this.taxID = taxID;
    }

    // Abstract Method generateReport implementation defined in Customer class 
    @Override
    public String generateReport() {
        return "Corporate Customer: " + getName() + ", ID: " + getId() + ", Discount Rate: " + getDiscountRate() + ", Country: " + country + ", Tax ID: " + taxID;
    }
}
