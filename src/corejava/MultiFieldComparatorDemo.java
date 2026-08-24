package corejava;

import java.util.*;

class Employee {

    private final int id;
    private final String name;
    private final String department;
    private final double salary;

    public Employee(int id, String name,
                    String department, double salary) {
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
        return String.format(
                "%d | %s | %s | %.2f",
                id, name, department, salary
        );
    }
}

public class MultiFieldComparatorDemo {

    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee(1, "Rahul", "IT", 90000),
                new Employee(2, "Amit", "HR", 70000),
                new Employee(3, "Priya", "IT", 100000),
                new Employee(4, "Neha", "HR", 80000),
                new Employee(5, "Vikas", "IT", 90000),
                new Employee(6, "Anita", "IT", 90000)
        );

        // Multiple-field sorting
        Comparator<Employee> comparator =
                Comparator
                        .comparing(Employee::getDepartment)
                        .thenComparing(
                                Employee::getSalary,
                                Comparator.reverseOrder()
                        )
                        .thenComparing(Employee::getName);

        employees.sort(comparator);

        employees.forEach(System.out::println);
    }
}
