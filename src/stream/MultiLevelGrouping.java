package stream;

/*
Employees
    |
    v
GROUP BY Department
    |
    +---------------- IT
    |                   |
    |                   +-- Developer -> [Amit, Priya]
    |                   |
    |                   +-- Manager   -> [Rahul]
    |
    +---------------- HR
    |                   |
    |                   +-- Manager   -> [Sneha]
    |                   |
    |                   +-- Recruiter -> [Vikas]
    |
    +---------------- Finance
                        |
                        +-- Analyst   -> [Neha, Pooja]
                        |
                        +-- Manager   -> [Ravi]
*/
import java.util.*;
import java.util.stream.Collectors;



public class MultiLevelGrouping {

    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
            new Employee(1, "Amit", "IT", "Developer", 90000),
            new Employee(2, "Priya", "IT", "Developer", 85000),
            new Employee(3, "Rahul", "IT", "Manager", 120000),
            new Employee(4, "Sneha", "HR", "Manager", 100000),
            new Employee(5, "Vikas", "HR", "Recruiter", 65000),
            new Employee(6, "Neha", "Finance", "Analyst", 75000),
            new Employee(7, "Ravi", "Finance", "Manager", 110000),
            new Employee(8, "Pooja", "Finance", "Analyst", 80000)
        );

        /*
         * Level 1  -> Group by Department
         * Level 2  -> Group by Designation
         * Downstream -> Collect employee names into List
         */

        Map<String, Map<String, List<String>>> result =
            employees.stream()
                .collect(
                    Collectors.groupingBy(
                        Employee::getDepartment,

                        Collectors.groupingBy(
                            Employee::getDesignation,

                            Collectors.mapping(
                                Employee::getName,
                                Collectors.toList()
                            )
                        )
                    )
                );

        // Display result
        result.forEach((department, designationMap) -> {

            System.out.println("Department: " + department);

            designationMap.forEach((designation, names) -> {

                System.out.println(
                    "   " + designation + " -> " + names
                );
            });

            System.out.println();
        });
    }
}
