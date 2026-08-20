package design_patterns;

class Employee implements Cloneable {

    private String name;
    private String department;

    public Employee(String name, String department) {
        this.name = name;
        this.department = department;
    }

    @Override
    public Employee clone() {
        try {
            return (Employee) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class Prototype {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee e1 = new Employee("Rahul", "IT");
		Employee e2 = e1.clone();
		System.out.println(e1==e2); // false

	}

}
