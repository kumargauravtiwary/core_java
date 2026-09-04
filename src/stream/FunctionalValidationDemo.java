package stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalValidationDemo {

    // --------------------------------------------------
    // DOMAIN OBJECT
    // --------------------------------------------------

    record User(
            String name,
            String email,
            int age,
            String password
    ) {
    }


    // --------------------------------------------------
    // VALIDATION FRAMEWORK
    // --------------------------------------------------

    static class Validator<T> {

        private final List<Function<T, Optional<String>>> rules;

        private Validator(
                List<Function<T, Optional<String>>> rules) {

            this.rules = rules;
        }

        // Create a validator with one rule
        public static <T> Validator<T> of(
                Function<T, Optional<String>> rule) {

            return new Validator<>(
                    List.of(rule)
            );
        }

        // Add another validation rule
        public Validator<T> and(
                Function<T, Optional<String>> rule) {

            List<Function<T, Optional<String>>> newRules =
                    new ArrayList<>(rules);

            newRules.add(rule);

            return new Validator<>(newRules);
        }

        // Execute all rules
        public List<String> validate(T value) {

            return rules.stream()

                    .map(rule -> rule.apply(value))

                    .flatMap(Optional::stream)

                    .toList();
        }

        // Check whether all rules pass
        public boolean isValid(T value) {

            return rules.stream()
                    .allMatch(rule ->
                            rule.apply(value).isEmpty());
        }
    }


    // --------------------------------------------------
    // REUSABLE VALIDATION RULES
    // --------------------------------------------------

    static Function<User, Optional<String>>
    nameRequired() {

        return user ->
                user.name() == null ||
                user.name().isBlank()

                ? Optional.of("Name is required")

                : Optional.empty();
    }


    static Function<User, Optional<String>>
    emailRequired() {

        return user ->
                user.email() == null ||
                user.email().isBlank()

                ? Optional.of("Email is required")

                : Optional.empty();
    }


    static Function<User, Optional<String>>
    validEmail() {

        return user ->
                user.email() != null &&
                user.email().matches(
                        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
                )

                ? Optional.empty()

                : Optional.of("Invalid email format");
    }


    static Function<User, Optional<String>>
    minimumAge(int minimumAge) {

        return user ->
                user.age() >= minimumAge

                ? Optional.empty()

                : Optional.of(
                        "User must be at least "
                                + minimumAge
                                + " years old"
                );
    }


    static Function<User, Optional<String>>
    minimumPasswordLength(int length) {

        return user ->
                user.password() != null &&
                user.password().length() >= length

                ? Optional.empty()

                : Optional.of(
                        "Password must contain at least "
                                + length
                                + " characters"
                );
    }


    static Function<User, Optional<String>>
    passwordContainsNumber() {

        return user ->
                user.password() != null &&
                user.password().matches(".*\\d.*")

                ? Optional.empty()

                : Optional.of(
                        "Password must contain a number"
                );
    }


    // --------------------------------------------------
    // MAIN
    // --------------------------------------------------

    public static void main(String[] args) {

        User user = new User(
                "",
                "invalid-email",
                16,
                "hello"
        );


        // Compose validation rules
        Validator<User> userValidator =
                Validator
                        .of(nameRequired())
                        .and(emailRequired())
                        .and(validEmail())
                        .and(minimumAge(18))
                        .and(minimumPasswordLength(8))
                        .and(passwordContainsNumber());


        // Execute validation
        List<String> errors =
                userValidator.validate(user);


        System.out.println("Validation errors:");

        errors.forEach(
                error -> System.out.println("❌ " + error)
        );


        System.out.println(
                "\nValid user? "
                        + userValidator.isValid(user)
        );
    }
}
