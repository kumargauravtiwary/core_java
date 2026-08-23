package corejava;

public class VarargsOverloadingDemo {

    // 1. Normal parameter
    static void test(int x) {
        System.out.println("test(int)");
    }

    // 2. Varargs parameter
    static void test(int... x) {
        System.out.println("test(int...)");
    }

    // 3. Different fixed parameter + varargs
    static void display(int x, int... y) {
        System.out.println("display(int, int...)");
    }

    static void display(int x, double... y) {
        System.out.println("display(int, double...)");
    }

    public static void main(String[] args) {

        System.out.println("---- Case 1 ----");

        test(10);

        /*
         * Output:
         * test(int)
         *
         * Why?
         * The compiler prefers an exact fixed-parameter match
         * over a varargs match.
         */


        System.out.println("\n---- Case 2 ----");

        test(10, 20);

        /*
         * Output:
         * test(int...)
         *
         * test(int) cannot accept two arguments.
         * Therefore varargs is selected.
         */


        System.out.println("\n---- Case 3 ----");

        test();

        /*
         * Output:
         * test(int...)
         *
         * Only the varargs method can accept zero arguments.
         */


        System.out.println("\n---- Case 4 ----");

        display(10, 20);

        /*
         * This call is AMBIGUOUS.
         *
         * Both methods can accept:
         *
         * display(int, int...)
         * display(int, double...)
         *
         * 20 can be used as either:
         *
         * int
         * double
         *
         * Therefore Java cannot choose a single best method.
         *
         * Uncommenting the call will produce a compilation error.
         */
    }
}
