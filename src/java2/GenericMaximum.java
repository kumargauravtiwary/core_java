package java2;

import java.util.*;

public class GenericMaximum {

    // Generic method with bounded type parameter
    public static <T extends Comparable<T>> T findMaximum(List<T> list) {

        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException(
                    "List must not be null or empty"
            );
        }

        T max = list.get(0);

        for (int i = 1; i < list.size(); i++) {

            if (list.get(i).compareTo(max) > 0) {
                max = list.get(i);
            }
        }

        return max;
    }

    public static void main(String[] args) {

        // Integer
        List<Integer> numbers =
                Arrays.asList(10, 50, 20, 90, 30);

        System.out.println(
                "Maximum number: " + findMaximum(numbers)
        );

        // Double
        List<Double> prices =
                Arrays.asList(10.5, 25.7, 15.2, 40.9);

        System.out.println(
                "Maximum price: " + findMaximum(prices)
        );

        // String
        List<String> names =
                Arrays.asList("Java", "Spring", "Kafka", "Docker");

        System.out.println(
                "Maximum string: " + findMaximum(names)
        );
    }
}
