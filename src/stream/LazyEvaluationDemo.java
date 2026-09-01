package stream;

/*
ALL elements
    ↓
peek()
    ↓
ALL elements
    ↓
filter()
    ↓
ALL elements
    ↓
map()
*/
import java.util.List;

public class LazyEvaluationDemo {

    public static void main(String[] args) {

        List<String> names = List.of(
                "Amit", "Rahul", "Ankit", "Priya", "Ravi"
        );

        System.out.println("Creating Stream...");

        var stream = names.stream()
                .peek(name -> System.out.println("peek 1: " + name))
                .filter(name -> {
                    System.out.println("filter: " + name);
                    return name.startsWith("A");
                })
                .peek(name -> System.out.println("peek 2: " + name))
                .map(name -> {
                    System.out.println("map: " + name);
                    return name.toUpperCase();
                });

        System.out.println("\nStream created.");
        System.out.println("Nothing has executed yet!");

        System.out.println("\nCalling terminal operation: forEach()");

        stream.forEach(name ->
                System.out.println("Result: " + name)
        );
    }
}
