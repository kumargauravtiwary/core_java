package corejava;

public class RecordDemo {

    public static void main(String[] args) {

        Customer customer =
                new Customer(101, "Gaurav", "gaurav@example.com");

        System.out.println(customer.id());
        System.out.println(customer.name());
        System.out.println(customer.email());

        System.out.println(customer);
        customer =
        new Customer(-1, "Gaurav", "gaurav@example.com");
    }
}
record Customer(
        long id,
        String name,
        String email
) {
    public Customer {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
    }
}
