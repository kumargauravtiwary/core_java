package stream;
/*
Department
    │
    ├── employees
    │      │
    │      ├── filter()
    │      │     salary >= 100000
    │      │
    │      └── map()
    │            Employee → Map
    │                 │
    │                 └── skills
    │                       │
    │                       └── stream()
    │                            sorted()
    │                            toList()
    │
    └── map()
          Department → Map
*/
import java.util.*;
import java.util.stream.Collectors;

public class NestedJsonTransformer {

    // =========================
    // INPUT OBJECTS
    // =========================

    record Employee(
            int id,
            String name,
            String role,
            double salary,
            List<String> skills
    ) {}

    record Department(
            int id,
            String name,
            List<Employee> employees
    ) {}

    public static void main(String[] args) {

        // ----------------------------------------
        // Sample nested data
        // ----------------------------------------

        List<Department> departments = List.of(

                new Department(
                        101,
                        "Engineering",
                        List.of(
                                new Employee(
                                        1,
                                        "Rahul",
                                        "Developer",
                                        120000,
                                        List.of("Java", "Spring", "AWS")
                                ),
                                new Employee(
                                        2,
                                        "Priya",
                                        "Developer",
                                        95000,
                                        List.of("Java", "Kafka")
                                ),
                                new Employee(
                                        3,
                                        "Amit",
                                        "Architect",
                                        180000,
                                        List.of("Java", "AWS", "Kubernetes")
                                )
                        )
                ),

                new Department(
                        102,
                        "Finance",
                        List.of(
                                new Employee(
                                        4,
                                        "Neha",
                                        "Analyst",
                                        80000,
                                        List.of("SQL", "Excel")
                                ),
                                new Employee(
                                        5,
                                        "Vikram",
                                        "Manager",
                                        140000,
                                        List.of("Finance", "Leadership")
                                )
                        )
                )
        );

        // =====================================================
        // STREAM-BASED TRANSFORMATION
        // =====================================================

        List<Map<String, Object>> result =
                departments.stream()

                        // Department transformation
                        .map(department -> {

                            // Transform nested employees
                            List<Map<String, Object>> employees =
                                    department.employees().stream()

                                            // Keep employees earning >= 100000
                                            .filter(employee ->
                                                    employee.salary() >= 100000)

                                            // Transform Employee object
                                            .map(employee -> {

                                                Map<String, Object> employeeJson =
                                                        new LinkedHashMap<>();

                                                employeeJson.put(
                                                        "employeeId",
                                                        employee.id()
                                                );

                                                employeeJson.put(
                                                        "name",
                                                        employee.name()
                                                );

                                                employeeJson.put(
                                                        "role",
                                                        employee.role()
                                                );

                                                employeeJson.put(
                                                        "salary",
                                                        employee.salary()
                                                );

                                                // Nested array transformation
                                                employeeJson.put(
                                                        "skills",
                                                        employee.skills()
                                                                .stream()
                                                                .sorted()
                                                                .toList()
                                                );

                                                return employeeJson;
                                            })

                                            .toList();

                            // Create department JSON-like object
                            Map<String, Object> departmentJson =
                                    new LinkedHashMap<>();

                            departmentJson.put(
                                    "departmentId",
                                    department.id()
                            );

                            departmentJson.put(
                                    "departmentName",
                                    department.name()
                            );

                            departmentJson.put(
                                    "employees",
                                    employees
                            );

                            return departmentJson;
                        })

                        .toList();


        // =====================================================
        // PRINT JSON-LIKE RESULT
        // =====================================================

        printJsonLike(result);
    }


    // =========================================================
    // Simple JSON-like printer
    // =========================================================

    private static void printJsonLike(Object object) {

        if (object instanceof Map<?, ?> map) {

            System.out.println("{");

            map.forEach((key, value) -> {

                System.out.print("  \"" + key + "\": ");

                printValue(value);

                System.out.println(",");
            });

            System.out.println("}");

        } else if (object instanceof List<?> list) {

            System.out.println("[");

            list.forEach(item -> {

                printValue(item);

                System.out.println(",");
            });

            System.out.println("]");

        } else {

            System.out.println(object);
        }
    }


    private static void printValue(Object value) {

        if (value instanceof Map<?, ?> map) {

            System.out.print("{ ");

            map.forEach((key, val) ->
                    System.out.print(
                            "\"" + key + "\": " + val + ", "
                    )
            );

            System.out.print("}");

        } else if (value instanceof List<?> list) {

            System.out.print(list);

        } else if (value instanceof String) {

            System.out.print("\"" + value + "\"");

        } else {

            System.out.print(value);
        }
    }
}
