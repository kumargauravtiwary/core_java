package stream;

import java.util.List;
import java.util.stream.Collectors;

public class TeeingDemo {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(
                10, 20, 30, 40, 50
        );

        var result = numbers.stream()
                .collect(Collectors.teeing(
                        // First aggregate: Sum
                        Collectors.summingInt(Integer::intValue),

                        // Second aggregate: Count
                        Collectors.counting(),

                        // Combine both results
                        (sum, count) -> {
                            double average = (double) sum / count;
                            return "Sum = " + sum +
                                   ", Count = " + count +
                                   ", Average = " + average;
                        }
                ));

        System.out.println(result);
    }
}
