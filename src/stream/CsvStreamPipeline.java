package stream;

/*
             employees.csv
                   │
                   ▼
          ┌─────────────────┐
          │   Files.lines() │
          └────────┬────────┘
                   │
                   ▼
          ┌─────────────────┐
          │     skip(1)     │
          │  Remove header  │
          └────────┬────────┘
                   │
                   ▼
          ┌─────────────────┐
          │      map()      │
          │ CSV → Employee  │
          └────────┬────────┘
                   │
                   ▼
          ┌─────────────────┐
          │    filter()     │
          │ Department=IT   │
          └────────┬────────┘
                   │
                   ▼
          ┌─────────────────┐
          │    filter()     │
          │ Salary >= 80K   │
          └────────┬────────┘
                   │
                   ▼
          ┌─────────────────┐
          │    sorted()     │
          │ Salary DESC     │
          └────────┬────────┘
                   │
                   ▼
          ┌─────────────────┐
          │    collect()    │
          │   List<Employee>│
          └─────────────────┘
*/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvStreamPipeline {

    public static void main(String[] args) {

        String fileName = "src\\stream\\employees.csv";

        try (Stream<String> lines = Files.lines(Path.of(fileName))) {

            List<Employee> employees = lines
                    // Skip CSV header
                    .skip(1)

                    // Convert CSV line to Employee
                    .map(CsvStreamPipeline::parseEmployee)

                    // Keep only IT employees
                    .filter(employee ->
                            employee.department().equalsIgnoreCase("IT"))

                    // Keep employees earning >= 80,000
                    .filter(employee ->
                            employee.salary() >= 80000)

                    // Sort by salary descending
                    .sorted((e1, e2) ->
                            Double.compare(e2.salary(), e1.salary()))

                    // Collect final result
                    .collect(Collectors.toList());

            System.out.println("Processed Employees:");
            System.out.println("--------------------");

            employees.forEach(System.out::println);

        } catch (IOException e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }
    }

    // Transform CSV line into Employee object
    private static Employee parseEmployee(String line) {

        String[] data = line.split(",");

        int id = Integer.parseInt(data[0]);
        String name = data[1];
        String department = data[2];
        double salary = Double.parseDouble(data[3]);

        return new Employee(
                id,
                name,
                department,
                salary
        );
    }

    record Employee(
            int id,
            String name,
            String department,
            double salary
    ) {}
}
