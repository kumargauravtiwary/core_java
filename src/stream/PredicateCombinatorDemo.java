package stream;

import java.util.List;
import java.util.function.Predicate;
/*
Example: Employee filtering

Requirement:

Employee must be active
AND salary > ₹80,000
AND either:
department is IT, OR
experience >= 8 years
AND employee must not be on probation
*/
public class PredicateCombinatorDemo {

    record Employee(
            String name,
            String department,
            double salary,
            int experience,
            boolean active,
            boolean probation
    ) {}

    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new Employee("Amit", "IT", 100000, 6, true, false),
                new Employee("Rahul", "HR", 90000, 10, true, false),
                new Employee("Priya", "Finance", 70000, 9, true, false),
                new Employee("Neha", "IT", 85000, 5, false, false),
                new Employee("Vikram", "Sales", 95000, 12, true, true),
                new Employee("Sneha", "IT", 120000, 10, true, false)
        );

        // Individual predicates
        Predicate<Employee> isActive =
                Employee::active;

        Predicate<Employee> highSalary =
                e -> e.salary() > 80000;

        Predicate<Employee> isIT =
                e -> e.department().equalsIgnoreCase("IT");

        Predicate<Employee> highlyExperienced =
                e -> e.experience() >= 8;

        Predicate<Employee> isOnProbation =
                Employee::probation;

        /*
         * Complex condition:
         *
         * active
         * AND salary > 80000
         * AND (IT OR experience >= 8)
         * AND NOT probation
         */
        Predicate<Employee> complexFilter =
                isActive
                        .and(highSalary)
                        .and(isIT.or(highlyExperienced))
                        .and(isOnProbation.negate());

        System.out.println("Eligible Employees:");

        employees.stream()
                .filter(complexFilter)
                .forEach(System.out::println);
    }
}
