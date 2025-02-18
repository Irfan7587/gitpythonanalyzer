package Question2;
/*
 *  *  * Mohammed Irfan Battegeri
 *  *  * Object-oriented Software Development Assignment 2 Question 2
 *  *  * Implementing the Report class
 *  *  * DePaul University
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Report {
    
    // Method to print the rental report on console
    public static void printToConsole(Reportable reportable) {
        System.out.println(reportable.generateReport());
    }

    // Method to write the report to a output csv file
    public static void persistToCSV(Reportable reportable, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(reportable.generateReport());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
