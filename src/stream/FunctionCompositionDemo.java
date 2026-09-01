package stream;
import java.util.function.Function;

public class FunctionCompositionDemo {

    public static void main(String[] args) {

        // Function 1: Add 10
        Function<Integer, Integer> add10 =
                x -> x + 10;

        // Function 2: Multiply by 2
        Function<Integer, Integer> multiply2 =
                x -> x * 2;

        // Function 3: Convert Integer to String
        Function<Integer, String> toString =
                x -> "Result = " + x;

        Function<String, String> trim =
        String::trim;

        Function<String, String> toLowerCase =
                String::toLowerCase;

        Function<String, String> addPrefix =
                name -> "User: " + name;

        Function<String, String> pipeline =
                trim
                .andThen(toLowerCase)
                .andThen(addPrefix);

               

        // --------------------------------------------------
        // 1. andThen()
        // --------------------------------------------------

        Function<Integer, String> andThenChain =
                add10
                        .andThen(multiply2)
                        .andThen(toString);

        System.out.println("Using andThen:");
        System.out.println(andThenChain.apply(5));


        // --------------------------------------------------
        // 2. compose()
        // --------------------------------------------------

        Function<Integer, String> composeChain =
                toString
                        .compose(multiply2)
                        .compose(add10);

        System.out.println("\nUsing compose:");
        System.out.println(composeChain.apply(5));


        // --------------------------------------------------
        // 3. Compare execution order
        // --------------------------------------------------

        Function<Integer, Integer> chain =
                add10.andThen(multiply2);

        int result = chain.apply(5);

        System.out.println("\n5 + 10 = " + (5 + 10));
        System.out.println("(5 + 10) * 2 = " + result);
        System.out.println(
                pipeline.apply("   KUMAR GAURAV   ")
        );
    }
}
