package stream;

import java.util.List;

public class ShortCircuitDemo {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(
                10, 20, 30, 40, 50, 60, 70, 80
        );

        // =====================================================
        // 1. anyMatch()
        // =====================================================
        System.out.println("===== anyMatch() =====");

        boolean hasNumberGreaterThan40 =
                numbers.stream()
                       .peek(n -> System.out.println("Checking: " + n))
                       .anyMatch(n -> n > 40);

        System.out.println("Result: " + hasNumberGreaterThan40);


        // =====================================================
        // 2. allMatch()
        // =====================================================
        System.out.println("\n===== allMatch() =====");

        boolean allNumbersGreaterThan5 =
                numbers.stream()
                       .peek(n -> System.out.println("Checking: " + n))
                       .allMatch(n -> n > 5);

        System.out.println("Result: " + allNumbersGreaterThan5);


        // =====================================================
        // 3. allMatch() - Early termination
        // =====================================================
        System.out.println("\n===== allMatch() Early Termination =====");

        boolean allNumbersGreaterThan25 =
                numbers.stream()
                       .peek(n -> System.out.println("Checking: " + n))
                       .allMatch(n -> n > 25);

        System.out.println("Result: " + allNumbersGreaterThan25);


        // =====================================================
        // 4. noneMatch()
        // =====================================================
        System.out.println("\n===== noneMatch() =====");

        boolean noNumberGreaterThan100 =
                numbers.stream()
                       .peek(n -> System.out.println("Checking: " + n))
                       .noneMatch(n -> n > 100);

        System.out.println("Result: " + noNumberGreaterThan100);


        // =====================================================
        // 5. noneMatch() - Early termination
        // =====================================================
        System.out.println("\n===== noneMatch() Early Termination =====");

        boolean noNumberGreaterThan50 =
                numbers.stream()
                       .peek(n -> System.out.println("Checking: " + n))
                       .noneMatch(n -> n > 50);

        System.out.println("Result: " + noNumberGreaterThan50);
    }
}
