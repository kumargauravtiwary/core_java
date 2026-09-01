package stream;

/*
                 Stream
                   |
                   v
              supplier()
                   |
                   v
             Statistics
                   |
             accumulator()
                   |
                   v
          Update min/max/sum
                   |
             combiner()
                   |
                   v
       Merge partial Statistics
                   |
             finisher()
                   |
                   v
             Statistics
*/
import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

public class CustomStatisticsCollector {

    // Result object
    static class Statistics {

        private long count;
        private double min = Double.POSITIVE_INFINITY;
        private double max = Double.NEGATIVE_INFINITY;
        private double sum = 0;

        public void accept(double value) {
            count++;
            sum += value;

            if (value < min) {
                min = value;
            }

            if (value > max) {
                max = value;
            }
        }

        public void combine(Statistics other) {
            count += other.count;
            sum += other.sum;

            min = Math.min(min, other.min);
            max = Math.max(max, other.max);
        }

        public double getMin() {
            return count == 0 ? 0 : min;
        }

        public double getMax() {
            return count == 0 ? 0 : max;
        }

        public double getAverage() {
            return count == 0 ? 0 : sum / count;
        }

        public long getCount() {
            return count;
        }

        @Override
        public String toString() {
            return "Statistics{" +
                    "count=" + count +
                    ", min=" + getMin() +
                    ", max=" + getMax() +
                    ", average=" + getAverage() +
                    '}';
        }
    }


    // Custom Collector
    static class StatisticsCollector
            implements Collector<Double, Statistics, Statistics> {

        @Override
        public Supplier<Statistics> supplier() {
            return Statistics::new;
        }

        @Override
        public BiConsumer<Statistics, Double> accumulator() {
            return Statistics::accept;
        }

        @Override
        public BinaryOperator<Statistics> combiner() {
            return (stats1, stats2) -> {
                stats1.combine(stats2);
                return stats1;
            };
        }

        @Override
        public Function<Statistics, Statistics> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.singleton(Characteristics.IDENTITY_FINISH);
        }
    }


    public static void main(String[] args) {

        List<Double> salaries = Arrays.asList(
                50000.0,
                75000.0,
                90000.0,
                60000.0,
                120000.0,
                80000.0
        );

        Statistics statistics =
                salaries.stream()
                        .collect(new StatisticsCollector());

        System.out.println("Count   : " + statistics.getCount());
        System.out.println("Minimum : " + statistics.getMin());
        System.out.println("Maximum : " + statistics.getMax());
        System.out.println("Average : " + statistics.getAverage());
    }
}
