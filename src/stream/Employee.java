package stream;

class Employee {

    private int id;
    private String name;
    private String department;
    private String designation;
    private double salary;

    public Employee(
            int id,
            String name,
            String department,
            String designation,
            double salary) {

        this.id = id;
        this.name = name;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getDesignation() {
        return designation;
    }

    public double getSalary() {
        return salary;
    }
}
