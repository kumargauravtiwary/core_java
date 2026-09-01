package stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ToMapMergeDemo {

    record Employee(
            int id,
            String name,
            String department,
            double salary
    ) {}

    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new Employee(101, "John", "IT", 75000),
                new Employee(102, "Alice", "HR", 65000),
                new Employee(103, "Bob", "IT", 95000),
                new Employee(104, "David", "Finance", 85000),
                new Employee(105, "Emma", "HR", 80000),
                new Employee(106, "Mike", "IT", 88000)
        );

        Map<String, Employee> highestPaidByDepartment =
                employees.stream()
                        .collect(Collectors.toMap(
                                Employee::department,   // Key
                                employee -> employee,   // Value

                                // Merge function
                                (emp1, emp2) ->
                                        emp1.salary() >= emp2.salary()
                                                ? emp1
                                                : emp2
                        ));

        highestPaidByDepartment.forEach(
                (department, employee) ->
                        System.out.println(
                                department + " -> " +
                                employee.name() + " : ₹" +
                                employee.salary()
                        )
        );
    }
}
