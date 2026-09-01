package stream;

import java.util.*;
import java.util.function.*;

public class MethodReferenceDemo {

    // -----------------------------
    // 1. STATIC METHOD
    // -----------------------------
    static class EmployeeUtil {

        public static boolean isHighSalary(Employee emp) {
            return emp.getSalary() >= 100000;
        }

        public static String formatEmployee(Employee emp) {
            return emp.getName() + " - ₹" + emp.getSalary();
        }
    }

    // -----------------------------
    // 2. INSTANCE METHOD
    // -----------------------------
    static class EmployeeService {

        public void printEmployee(Employee emp) {
            System.out.println(
                "Employee: " + emp.getName() +
                ", Salary: ₹" + emp.getSalary()
            );
        }
    }

    // -----------------------------
    // 3. CONSTRUCTOR
    // -----------------------------
    static class EmployeeDTO {

        private String name;
        private double salary;

        public EmployeeDTO(Employee emp) {
            this.name = emp.getName();
            this.salary = emp.getSalary();
        }

        @Override
        public String toString() {
            return "EmployeeDTO{name='" + name +
                   "', salary=" + salary + "}";
        }
    }

    // -----------------------------
    // EMPLOYEE CLASS
    // -----------------------------
    static class Employee {

        private String name;
        private double salary;

        public Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return name + " - ₹" + salary;
        }
    }

    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
            new Employee("Rahul", 80000),
            new Employee("Priya", 120000),
            new Employee("Amit", 150000),
            new Employee("Neha", 90000)
        );

        // =====================================================
        // 1. STATIC METHOD REFERENCE
        // =====================================================

        System.out.println("=== Static Method Reference ===");

        // Lambda
        employees.stream()
                 .filter(emp -> EmployeeUtil.isHighSalary(emp))
                 .forEach(System.out::println);

        // Method reference
        employees.stream()
                 .filter(EmployeeUtil::isHighSalary)
                 .forEach(System.out::println);


        // =====================================================
        // 2. INSTANCE METHOD REFERENCE
        // =====================================================

        System.out.println("\n=== Instance Method Reference ===");

        EmployeeService service = new EmployeeService();

        // Lambda
        employees.forEach(emp -> service.printEmployee(emp));

        // Method reference
        employees.forEach(service::printEmployee);


        // =====================================================
        // 3. INSTANCE METHOD OF AN ARBITRARY OBJECT
        // =====================================================

        System.out.println("\n=== Existing Instance Method ===");

        // Lambda
        employees.stream()
                 .map(emp -> emp.getName())
                 .forEach(name -> System.out.println(name));

        // Method references
        employees.stream()
                 .map(Employee::getName)
                 .forEach(System.out::println);


        // =====================================================
        // 4. CONSTRUCTOR METHOD REFERENCE
        // =====================================================

        System.out.println("\n=== Constructor Method Reference ===");

        // Lambda
        List<EmployeeDTO> dtoList1 =
                employees.stream()
                         .map(emp -> new EmployeeDTO(emp))
                         .toList();

        // Constructor reference
        List<EmployeeDTO> dtoList2 =
                employees.stream()
                         .map(EmployeeDTO::new)
                         .toList();

        dtoList2.forEach(System.out::println);


        // =====================================================
        // 5. STATIC METHOD REFERENCE WITH MAP
        // =====================================================

        System.out.println("\n=== Static Method Reference with map ===");

        // Lambda
        employees.stream()
                 .map(emp -> EmployeeUtil.formatEmployee(emp))
                 .forEach(System.out::println);

        // Method reference
        employees.stream()
                 .map(EmployeeUtil::formatEmployee)
                 .forEach(System.out::println);
    }
}
