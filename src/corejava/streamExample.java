package corejava;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class streamExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//create a list of employee with name and age
		Employee e1 = new Employee("John", 25);
		Employee e2 = new Employee("Alice", 30);
		Employee e3 = new Employee("Bob", 28);
			
		//create a list of employee
		List<Employee> employees = new ArrayList<>();
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
		//call the filterEmployeeByAge function to filter employee by age > 28
		List<Employee> filteredEmployees = filterEmployeeByAge(employees, 28);
		//print the filtered employee
		for (Employee e : filteredEmployees) {
			System.out.println(e.name + " " + e.age);
		}
			
		
	}
	//streaming function to filter employee by age > 28
	public static List<Employee> filterEmployeeByAge(List<Employee> employees, int age) {
		
		return employees.stream().filter(e -> e.getAge() > age).collect(Collectors.toList());
		
	}
	
		

}
