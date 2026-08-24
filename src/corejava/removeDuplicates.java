package corejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class removeDuplicates {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(
                10, 20, 10, 30, 20, 40, 50, 30
        );

        List<Integer> uniqueNumbers = new ArrayList<>();

        for (Integer number : numbers) {
            if (!uniqueNumbers.contains(number)) {
                uniqueNumbers.add(number);
            }
        }

        System.out.println("Original List: " + numbers);
        System.out.println("After Removing Duplicates: " + uniqueNumbers);
    }
}
