package java2;

/*
                User
                  │
        ┌─────────┴─────────┐
        ▼         ▼         ▼
      Name      Email      Age
        │         │         │
        ▼         ▼         ▼
     Error?     Error?     Error?
        │         │         │
        └────┬────┴────┬────┘
             ▼         ▼
          Error Collection
                 │
                 ▼
    CompositeValidationException
                 │
                 ▼
          ALL field errors
*/

import java.util.*;

public class ValidationPipeline {

    public static void main(String[] args) {

        User user = new User(
                "",
                "invalid-email",
                15,
                ""
        );

        try {
            validate(user);
            System.out.println("User is valid");

        } catch (CompositeValidationException e) {

            System.out.println("Validation failed:");

            for (FieldError error : e.getErrors()) {
                System.out.println(
                        error.field() + " -> " + error.message()
                );
            }
        }
    }

    public static void validate(User user) {

        List<FieldError> errors = new ArrayList<>();

        // Name validation
        if (user.name() == null || user.name().isBlank()) {
            errors.add(new FieldError(
                    "name",
                    "Name is required"
            ));
        }

        // Email validation
        if (user.email() == null ||
                !user.email().matches(
                        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            errors.add(new FieldError(
                    "email",
                    "Invalid email address"
            ));
        }

        // Age validation
        if (user.age() < 18) {
            errors.add(new FieldError(
                    "age",
                    "Age must be at least 18"
            ));
        }

        // Phone validation
        if (user.phone() == null ||
                user.phone().isBlank()) {

            errors.add(new FieldError(
                    "phone",
                    "Phone number is required"
            ));
        }

        // Throw ONE exception containing ALL errors
        if (!errors.isEmpty()) {
            throw new CompositeValidationException(errors);
        }
    }

    record User(
            String name,
            String email,
            int age,
            String phone
    ) {}

    record FieldError(
            String field,
            String message
    ) {}

    static class CompositeValidationException
            extends RuntimeException {

        private final List<FieldError> errors;

        public CompositeValidationException(
                List<FieldError> errors) {

            super("Validation failed with "
                    + errors.size() + " error(s)");

            this.errors = List.copyOf(errors);
        }

        public List<FieldError> getErrors() {
            return errors;
        }
    }
}
