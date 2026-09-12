package recursionandbacktracking;

import java.util.HashMap;
import java.util.Map;

public class RecursiveMath {

    // -------------------------
    // 1. Factorial
    // -------------------------

    private static final Map<Integer, Long> factorialMemo = new HashMap<>();

    public static long factorial(int n) {

        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }

        // Base case
        if (n == 0 || n == 1) {
            return 1;
        }

        // Already calculated?
        if (factorialMemo.containsKey(n)) {
            return factorialMemo.get(n);
        }

        // Recursive calculation
        long result = n * factorial(n - 1);

        // Store result
        factorialMemo.put(n, result);

        return result;
    }


    // -------------------------
    // 2. Fibonacci
    // -------------------------

    private static final Map<Integer, Long> fibonacciMemo = new HashMap<>();

    public static long fibonacci(int n) {

        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }

        // Base cases
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        // Already calculated?
        if (fibonacciMemo.containsKey(n)) {
            return fibonacciMemo.get(n);
        }

        // Recursive calculation
        long result =
                fibonacci(n - 1) +
                fibonacci(n - 2);

        // Store result
        fibonacciMemo.put(n, result);

        return result;
    }


    // -------------------------
    // 3. Power
    // -------------------------

    private static final Map<String, Double> powerMemo = new HashMap<>();

    public static double power(double base, int exponent) {

        // Base case
        if (exponent == 0) {
            return 1;
        }

        String key = base + ":" + exponent;

        // Already calculated?
        if (powerMemo.containsKey(key)) {
            return powerMemo.get(key);
        }

        double result;

        if (exponent < 0) {
            result = 1 / power(base, -exponent);
        } else {

            // Recursive calculation
            result = base * power(base, exponent - 1);
        }

        // Store result
        powerMemo.put(key, result);

        return result;
    }


    // -------------------------
    // Main
    // -------------------------

    public static void main(String[] args) {

        System.out.println("Factorial:");
        System.out.println("5! = " + factorial(5));
        System.out.println("10! = " + factorial(10));

        System.out.println();

        System.out.println("Fibonacci:");
        System.out.println("fib(10) = " + fibonacci(10));
        System.out.println("fib(20) = " + fibonacci(20));

        System.out.println();

        System.out.println("Power:");
        System.out.println("2^10 = " + power(2, 10));
        System.out.println("2^-3 = " + power(2, -3));
    }
}
