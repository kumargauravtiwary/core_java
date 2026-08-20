package corejava;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class test3 {

	    public static void main(String[] args) {

	        // --------------------------------------------------------------
	        // PITFALL 1: Unboxing null in arithmetic / concatenation
	        // --------------------------------------------------------------
	        Integer nullInteger = null;
	        try {
	            int result = nullInteger + 5;  // unboxes nullInteger → NPE
	            System.out.println("Result: " + result);
	        } catch (NullPointerException e) {
	            System.out.println("1️⃣ NPE on arithmetic: " + e);
	        }

	        // --------------------------------------------------------------
	        // PITFALL 2: Unboxing null from a Collection
	        // --------------------------------------------------------------
	        List<Integer> numbers = Arrays.asList(10, 20, null, 40);
	        int sum = 0;
	        try {
	            for (Integer num : numbers) {
	                sum += num;  // when num == null, unboxing triggers NPE
	            }
	        } catch (NullPointerException e) {
	            System.out.println("2️⃣ NPE in loop: " + e);
	        }

	        // --------------------------------------------------------------
	        // PITFALL 3: Unboxing null in method arguments
	        // --------------------------------------------------------------
	        Integer nullable = null;
	        try {
	            printLength(nullable);  // passes null, method unboxes → NPE
	        } catch (NullPointerException e) {
	            System.out.println("3️⃣ NPE in method arg: " + e);
	        }

	        // --------------------------------------------------------------
	        // PITFALL 4: Ternary operator with mixed types (classic trap!)
	        // --------------------------------------------------------------
	        Integer count = null;
	        try {
	            // The ternary forces unboxing because the false branch is int.
	            // Java tries to evaluate both branches to a common type (int),
	            // so count gets unboxed even if condition is false!
	            int value = (false) ? count : 100;
	            System.out.println("Value: " + value);
	        } catch (NullPointerException e) {
	            System.out.println("4️⃣ NPE in ternary: " + e);
	        }

	        // --------------------------------------------------------------
	        // SAFE ALTERNATIVE: explicit null-check
	        // --------------------------------------------------------------
	        Integer safeInt = null;
	        int defaultValue = 0;
	        int safeResult = (safeInt != null) ? safeInt : defaultValue;
	        System.out.println("✅ Safe result: " + safeResult);

	        // Or use Objects.toString / Optional (Java 8+)
	        Integer another = null;
	        int safeSum = Optional.ofNullable(another).orElse(0) + 10;
	        System.out.println("✅ Safe sum: " + safeSum);
	    }

	    // Method that expects a primitive int – unboxing happens at call site
	    public static void printLength(int number) {
	        System.out.println("Number is: " + number);
	    }
}
