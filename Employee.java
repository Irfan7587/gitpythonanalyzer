public class Employee{
    private String name;
    private int employeeID;
    private int yearsOfService;
    private double baseSalary;

    public Employee(String name, int employeeID, int yearsOfService, double baseSalary) {
        this.name = name;
        this.employeeID = employeeID;
        this.yearsOfService = yearsOfService;
        this.baseSalary = baseSalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getYearsOfService() {
        return yearsOfService;
    }

    public void setYearsOfService(int yearsOfService) {
        this.yearsOfService = yearsOfService;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double calculateSalary(){
        return getBaseSalary() + (0.05 * getYearsOfService() * getBaseSalary());
    }
}