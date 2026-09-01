package stream;

@FunctionalInterface
interface Validator<T> {

    boolean validate(T value);

    // Default method for validation chaining
    default Validator<T> and(Validator<T> other) {
        return value -> this.validate(value) && other.validate(value);
    }

    // Another default method
    default Validator<T> or(Validator<T> other) {
        return value -> this.validate(value) || other.validate(value);
    }

    // Negates the validation
    default Validator<T> negate() {
        return value -> !this.validate(value);
    }
}

public class ValidationChainingDemo {

    public static void main(String[] args) {

        // Validation 1: Name should not be null or empty
        Validator<String> notEmpty =
                value -> value != null && !value.isBlank();

        // Validation 2: Name should have at least 3 characters
        Validator<String> minThreeChars =
                value -> value != null && value.length() >= 3;

        // Validation 3: Name should contain only alphabets
        Validator<String> onlyLetters =
                value -> value != null && value.matches("[a-zA-Z]+");

        // Chain validations
        Validator<String> validName =
                notEmpty
                    .and(minThreeChars)
                    .and(onlyLetters);

        // Test values
        String[] names = {
                "John",
                "Al",
                "",
                "John123",
                null,
                "Alice"
        };

        for (String name : names) {

            boolean result = validName.validate(name);

            System.out.println(
                    "Name: " + name +
                    " -> Valid: " + result
            );
        }
    }
}
