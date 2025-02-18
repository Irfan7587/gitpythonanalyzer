package Question2;
/*
 *  *  * Mohammed Irfan Battegeri
 *  *  * Object-oriented Software Development Assignment 2 Question 2
 *  *  * Implementing Individual class
 *  *  * DePaul University
 */

public class Individual extends Customer {
    // Declaration of instance variables
    private int age;
    private String state;
    private String zip;

    //Constructor for Individual class
    public Individual(int id, String name, double discountRate, int age, String state, String zip) {
        super(id, name, discountRate);
        this.age = age;
        this.state = state;
        this.zip = zip;
    }

    // Abstract Method generateReport implementation defined in Customer class
    @Override
    public String generateReport() {
        return "Individual Customer: " + getName() + ", ID: " + getId() + ", Discount Rate: " + getDiscountRate() + ", Age: " + age + ", State: " + state + ", ZIP: " + zip;
    }
    
}
