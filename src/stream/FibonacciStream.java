package stream;

import java.util.stream.Stream;

public class FibonacciStream {

    public static void main(String[] args) {

        int n = 10;

        Stream.iterate(
                new long[]{0, 1},
                pair -> new long[]{pair[1], pair[0] + pair[1]}
        )
        .limit(n)
        .map(pair -> pair[0])
        .forEach(System.out::println);
    }
}
