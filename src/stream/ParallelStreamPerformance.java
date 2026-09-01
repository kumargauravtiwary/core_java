package stream;

import java.util.List;
import java.util.stream.IntStream;

public class ParallelStreamPerformance {

    public static void main(String[] args) {

        int size = 10_000_000;

        List<Integer> numbers = IntStream.rangeClosed(1, size)
                .boxed()
                .toList();

        System.out.println("Elements: " + numbers.size());
        System.out.println("Available CPU cores: "
                + Runtime.getRuntime().availableProcessors());

        // Warm-up JVM
        numbers.stream()
                .mapToLong(ParallelStreamPerformance::expensiveOperation)
                .sum();

        numbers.parallelStream()
                .mapToLong(ParallelStreamPerformance::expensiveOperation)
                .sum();

        // Sequential execution
        long startSequential = System.nanoTime();

        long sequentialResult = numbers.stream()
                .mapToLong(ParallelStreamPerformance::expensiveOperation)
                .sum();

        long endSequential = System.nanoTime();

        // Parallel execution
        long startParallel = System.nanoTime();

        long parallelResult = numbers.parallelStream()
                .mapToLong(ParallelStreamPerformance::expensiveOperation)
                .sum();

        long endParallel = System.nanoTime();

        double sequentialTime =
                (endSequential - startSequential) / 1_000_000.0;

        double parallelTime =
                (endParallel - startParallel) / 1_000_000.0;

        System.out.println("\n----- Results -----");

        System.out.println("Sequential result : " + sequentialResult);
        System.out.println("Parallel result   : " + parallelResult);

        System.out.printf(
                "Sequential time   : %.2f ms%n",
                sequentialTime
        );

        System.out.printf(
                "Parallel time     : %.2f ms%n",
                parallelTime
        );

        System.out.printf(
                "Speedup            : %.2fx%n",
                sequentialTime / parallelTime
        );
    }

    private static long expensiveOperation(int n) {

        long result = 0;

        for (int i = 0; i < 100; i++) {
            result += (long) n * i;
            result = result % 1_000_000_007;
        }

        return result;
    }
}
