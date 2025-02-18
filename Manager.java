/***
 * Object-Oriented-Programming Midterm Question
 * Implementing Manager class 
 * Mohammed Irfan Battegeri
 * DePaul University
 ***/


import java.util.*;

public class Manager extends Employee{
    // Declaration of instance variables 
    private List<Employee> subordinates;
    
    // Constructor without list of subordinates
    public Manager (String name, int employeeID, int yearsOfService, double baseSalary){
        super(name, employeeID, yearsOfService, baseSalary);
        this.subordinates = new ArrayList<>();
    }

    // Constructor with List of Subordinates
    public Manager (String name, int employeeID, int yearsOfService, double baseSalary, List<Employee> subordinates){
        super(name, employeeID, yearsOfService, baseSalary);
        this.subordinates = subordinates;
    }

    // Method to calculate the salary of the manager without bonus 
    @Override
    public double calculateSalary(){
        return getBaseSalary() + (0.05 * getYearsOfService() * getBaseSalary()) + (0.02 * getSubordinateCount() * getBaseSalary());
    }

    // Method to calculate the salary of the manager with bonus
    public double calculateSalary(boolean bonus){
        double regularSalary = calculateSalary();
        if(bonus){
            return regularSalary + (0.1 * getBaseSalary());
        } 
        return regularSalary;
    }
 

    // Method to add an employee to the subordinates array
    public void addSubordinate(Employee subordinate){
        subordinates.add(subordinate);
        System.out.println("Subordinate added successfully");
    }

    // Method to remove an employee from the subordinates array
    public void removeSubordinate(Employee subordinate){
        subordinates.remove(subordinate);
        System.out.println("Subordinate removed successfully");
    }

    // Method to get the number of subordinates
    public int getSubordinateCount(){
        return subordinates.size();
    }


}