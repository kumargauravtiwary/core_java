package corejava;
import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return name + " (" + salary + ")";
    }
}

public class EmployeeDataPipeline {

    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
            new Employee(1, "Amit", "IT", 90000),
            new Employee(2, "Priya", "HR", 60000),
            new Employee(3, "Rahul", "IT", 75000),
            new Employee(4, "Sneha", "Finance", 85000),
            new Employee(5, "Vikas", "HR", 55000),
            new Employee(6, "Neha", "IT", 95000),
            new Employee(7, "Ravi", "Finance", 70000)
        );

        // Data Pipeline
        Map<String, List<String>> employeesByDepartment =
                employees.stream()

                        // 1. FILTER
                        .filter(employee -> employee.getSalary() >= 70000)

                        // 2. GROUP BY DEPARTMENT
                        .collect(Collectors.groupingBy(

                                Employee::getDepartment,

                                // 3. MAP employee -> employee name
                                Collectors.mapping(
                                        Employee::getName,

                                        // 4. COLLECT names into List
                                        Collectors.toList()
                                )
                        ));

        // Display result
        employeesByDepartment.forEach((department, employeeNames) -> {
            System.out.println(department + " -> " + employeeNames);
        });
    }
}