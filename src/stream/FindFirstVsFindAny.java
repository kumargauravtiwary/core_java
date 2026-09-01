package stream;

import java.util.stream.IntStream;

public class FindFirstVsFindAny {

    public static void main(String[] args) {

        System.out.println("findFirst():");

        Integer first = IntStream.rangeClosed(1, 20)
                .boxed()
                .parallel()
                .filter(n -> n > 10)
                .findFirst()
                .orElse(-1);

        System.out.println("Result: " + first);


        System.out.println("\nfindAny():");

        Integer any = IntStream.rangeClosed(1, 20)
                .boxed()
                .parallel()
                .filter(n -> n > 10)
                .findAny()
                .orElse(-1);

        System.out.println("Result: " + any);
    }
}
