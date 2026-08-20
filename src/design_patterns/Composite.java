package design_patterns;

import java.util.ArrayList;
import java.util.List;

//Treats individual objects and groups of objects uniformly.

interface Employee1 {
    void showDetails();
}

class Developer implements Employee1 {

    private final String name;

    public Developer(String name) {
        this.name = name;
    }

    public void showDetails() {
        System.out.println("Developer: " + name);
    }
}

class Manager implements Employee1 {

    private final String name;
    private final List<Employee1> employees = new ArrayList<>();

    public Manager(String name) {
        this.name = name;
    }

    public void addEmployee(Employee1 employee) {
        employees.add(employee);
    }

    public void showDetails() {

        System.out.println("Manager: " + name);

        for (Employee1 employee : employees) {
            employee.showDetails();
        }
    }
}

public class Composite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager manager = new Manager("Raj");

		manager.addEmployee(new Developer("Amit"));
		manager.addEmployee(new Developer("Ravi"));

		manager.showDetails();

	}

}
