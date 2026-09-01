package stream;

import java.util.*;
import java.util.stream.Collectors;

public class MapVsFlatMap {

    public static void main(String[] args) {

        List<List<String>> departments = Arrays.asList(

                Arrays.asList("Amit", "Rahul", "Neha"),

                Arrays.asList("Priya", "Sneha"),

                Arrays.asList("Vikas", "Ravi", "Pooja")
        );

        // -----------------------------------
        // 1. Using map()
        // -----------------------------------

        List<List<String>> resultUsingMap =
                departments.stream()
                        .map(list -> list)
                        .collect(Collectors.toList());

        System.out.println("Using map():");
        System.out.println(resultUsingMap);


        // -----------------------------------
        // 2. Using flatMap()
        // -----------------------------------

        List<String> resultUsingFlatMap =
                departments.stream()
                        .flatMap(List::stream)
                        .collect(Collectors.toList());

        System.out.println("\nUsing flatMap():");
        System.out.println(resultUsingFlatMap);
    }
}
