package Question2;
/*
 *  *  * Mohammed Irfan Battegeri
 *  *  * Object-oriented Software Development Assignment 2 Question 2
 *  *  * Implementing RentalOffice class
 *  *  * DePaul University
 */

import java.util.ArrayList;
import java.util.List;

public class RentalOffice implements Reportable {
    // Declaration of instance variables
    private int officeID;
    private String locationZip;
    private List<Inventory> inventories;
    private List<RentalOrder> rentalOrders;

    // Constructor for RentalOffice
    public RentalOffice(int officeID, String locationZip) {
        this.officeID = officeID;
        this.locationZip = locationZip;
        this.inventories = new ArrayList<>();
        this.rentalOrders = new ArrayList<>();
    }

    // Method to add a new inventory
    public void addInventory(Inventory inventory) {
        for (Inventory inv : inventories) {
            if (inv.getInventoryType() == inventory.getInventoryType()) {
                System.out.println("Inventory of this type already exists");
                return;
            }
        }
        inventories.add(inventory);
        System.out.println("New inventory added successfully");
    }

    // Method to remove a existing inventory
    public void removeInventory(Inventory inventory) {
        inventories.remove(inventory);
        System.out.println("Inventory removed successfully");
    }

    // Implementation of generateReport method which displays office details, inventory details, vehicle details in each inventory and order history
    @Override
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Office ID: ").append(officeID).append("\n");
        report.append("Location ZIP: ").append(locationZip).append("\n");
        report.append("Inventories:\n");
        for (Inventory inventory : inventories) {
            report.append("- ").append(inventory.getInventoryType()).append(" (ID: ").append(inventory.getInventoryID()).append(")\n");
            for (Vehicle vehicle : inventory.getListOfVehicles()) {
                report.append("  * Vehicle ID: ").append(vehicle.getVehicleID())
                .append(", Make: ").append(vehicle.getMake())
                .append(", Model: ").append(vehicle.getModel())
                .append(", Year: ").append(vehicle.getYear())
                .append(", Mileage: ").append(vehicle.getMileage()).append(" miles")
                .append(", Type: ").append(vehicle.getCarType())
                .append(", Available: ").append(vehicle.isAvailable())
                .append(", Daily Rate: $").append(vehicle.getDailyRate())
                .append("\n");

            }
        }
        report.append("Order History:\n");
        for (RentalOrder order : rentalOrders) {
            report.append("Customer: ").append(order.getCustomer().getName())
            .append(", Vehicle: ").append(order.getVehicle().getCarType())
            .append(", Rental Length: ").append(order.getRentalLength())
            .append(" days, Total Cost: $").append(order.getTotalCost())
            .append("\n");

        }
        return report.toString();
    }

    // Method to add a order to the rental order list
    public void addOrder(RentalOrder order) {
        rentalOrders.add(order);
    }

    // Method to remove a order from the rental order list
    public void removeOrder(RentalOrder order) {
        rentalOrders.remove(order);
    }

}
