package corejava;

// /Write a program using TreeSet to maintain a sorted, deduplicated collection of customobjects.

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

class Employee {

    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}

public class TreeSetCustomObjectDemo {

    public static void main(String[] args) {

        /*
         * TreeSet uses Comparator to:
         * 1. Sort objects
         * 2. Determine duplicates
         */
        Set<Employee> employees = new TreeSet<>(
                Comparator
                        .comparingDouble(Employee::getSalary)
                        .reversed()
                        .thenComparing(Employee::getName)
                        .thenComparingInt(Employee::getId)
        );

        employees.add(new Employee(101, "Rahul", 80000));
        employees.add(new Employee(102, "Amit", 95000));
        employees.add(new Employee(103, "Priya", 75000));
        employees.add(new Employee(104, "Neha", 95000));

        // Duplicate
        employees.add(new Employee(102, "Amit", 95000));

        employees.add(new Employee(105, "Ravi", 85000));

        System.out.println("Employees:");

        for (Employee employee : employees) {
            System.out.println(employee);
        }

        System.out.println("\nTotal employees: " + employees.size());
    }
}
