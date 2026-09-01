package stream;

/*
User
 │
 │ map()
 ▼
Address
 │
 │ map()
 ▼
City
 │
 │ filter()
 ▼
Valid City
 │
 │ orElseGet()
 ▼
Default City

*/
import java.util.Optional;

public class OptionalChainingDemo {

    public static void main(String[] args) {

        // Simulating data received from a database/API
        User user = new User(
                "Kumar",
                new Address("Bengaluru", "560035")
        );

        // ----------------------------------------------------
        // Traditional approach: multiple null checks
        // ----------------------------------------------------

        String city = null;

        if (user != null) {
            if (user.getAddress() != null) {
                if (user.getAddress().getCity() != null) {
                    city = user.getAddress().getCity();
                }
            }
        }

        System.out.println("Traditional city: " + city);


        // ----------------------------------------------------
        // Optional chaining
        // ----------------------------------------------------

        String optionalCity = Optional.ofNullable(user)

                // Extract Address
                .map(User::getAddress)

                // Extract city
                .map(Address::getCity)

                // Only accept cities with at least 3 characters
                .filter(c -> c.length() >= 3)

                // Generate fallback lazily
                .orElseGet(() -> {
                    System.out.println("City unavailable - generating default");
                    return "Unknown City";
                });

        System.out.println("Optional city: " + optionalCity);


        // ----------------------------------------------------
        // Example with null User
        // ----------------------------------------------------

        User nullUser = null;

        String result = Optional.ofNullable(nullUser)
                .map(User::getAddress)
                .map(Address::getCity)
                .filter(c -> c.length() >= 3)
                .orElseGet(() -> {
                    System.out.println("Fallback executed");
                    return "Default City";
                });

        System.out.println("Result: " + result);


        // ----------------------------------------------------
        // Example: filter rejecting the value
        // ----------------------------------------------------

        User userWithShortCity =
                new User(
                        "Rahul",
                        new Address("NY", "10001")
                );

        String filteredCity = Optional.ofNullable(userWithShortCity)
                .map(User::getAddress)
                .map(Address::getCity)
                .filter(c -> c.length() >= 3)
                .orElseGet(() -> "City does not satisfy requirement");

        System.out.println("Filtered city: " + filteredCity);
    }
}


// -----------------------------
// User class
// -----------------------------

class User {

    private final String name;
    private final Address address;

    public User(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }
}


// -----------------------------
// Address class
// -----------------------------

class Address {

    private final String city;
    private final String pinCode;

    public Address(String city, String pinCode) {
        this.city = city;
        this.pinCode = pinCode;
    }

    public String getCity() {
        return city;
    }

    public String getPinCode() {
        return pinCode;
    }
}
