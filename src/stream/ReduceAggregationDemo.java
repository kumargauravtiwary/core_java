package stream;

import java.util.Arrays;
import java.util.List;

class Employee1 {
    private String name;
    private double salary;

    public Employee1(String name, double salary) {
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
        return name + " - " + salary;
    }
}

class SalaryStats {

    private double totalSalary;
    private double minSalary = Double.MAX_VALUE;
    private double maxSalary = Double.MIN_VALUE;
    private int count;

    public SalaryStats() {
    }

    public SalaryStats(double totalSalary,
                       double minSalary,
                       double maxSalary,
                       int count) {
        this.totalSalary = totalSalary;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.count = count;
    }

    // Add one employee's salary
    public SalaryStats add(Employee1 employee) {

        double salary = employee.getSalary();

        totalSalary += salary;
        minSalary = Math.min(minSalary, salary);
        maxSalary = Math.max(maxSalary, salary);
        count++;

        return this;
    }

    // Combine two partial results
    public SalaryStats combine(SalaryStats other) {

        totalSalary += other.totalSalary;
        minSalary = Math.min(minSalary, other.minSalary);
        maxSalary = Math.max(maxSalary, other.maxSalary);
        count += other.count;

        return this;
    }

    public double getAverageSalary() {
        return count == 0 ? 0 : totalSalary / count;
    }

    @Override
    public String toString() {
        return "SalaryStats{" +
                "totalSalary=" + totalSalary +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                ", averageSalary=" + getAverageSalary() +
                ", count=" + count +
                '}';
    }
}

public class ReduceAggregationDemo {

    public static void main(String[] args) {

        List<Employee1> employees = Arrays.asList(
                new Employee1("John", 50000),
                new Employee1("Alice", 70000),
                new Employee1("Bob", 45000),
                new Employee1("David", 90000),
                new Employee1("Emma", 60000)
        );

        SalaryStats result =
                employees.stream()
                        .reduce(
                                new SalaryStats(),

                                (stats, employee) ->
                                        stats.add(employee),

                                (stats1, stats2) ->
                                        stats1.combine(stats2)
                        );

        System.out.println(result);
    }
}
