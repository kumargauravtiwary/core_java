package corejava;

public class OverloadingResolutionDemo {

    // 1. Exact match
    static void display(int x) {
        System.out.println("display(int)");
    }

    static void display(long x) {
        System.out.println("display(long)");
    }

    static void display(double x) {
        System.out.println("display(double)");
    }

    // 2. Reference type overloads
    static void show(String s) {
        System.out.println("show(String)");
    }

    static void show(Object o) {
        System.out.println("show(Object)");
    }

    // 3. Varargs
    static void print(int x) {
        System.out.println("print(int)");
    }

    static void print(int... x) {
        System.out.println("print(int...)");
    }


    public static void main(String[] args) {

        // =====================================================
        // CASE 1: Exact match wins
        // =====================================================

        display(10);

        // Output:
        // display(int)


        // =====================================================
        // CASE 2: Widening conversion
        // =====================================================

        short s = 10;

        display(s);

        // short -> int
        // Output:
        // display(int)


        // =====================================================
        // CASE 3: Widening hierarchy
        // =====================================================

        display(10L);

        // long is an exact match
        // Output:
        // display(long)


        // =====================================================
        // CASE 4: Reference type - most specific method
        // =====================================================

        show("Hello");

        // String is more specific than Object
        // Output:
        // show(String)


        // =====================================================
        // CASE 5: Varargs has lower priority
        // =====================================================

        print(10);

        // Exact match is preferred over varargs
        // Output:
        // print(int)


        // =====================================================
        // CASE 6: AMBIGUOUS CALL
        // =====================================================

        /*
         * Uncomment the following method definitions and call
         * to see an ambiguous method invocation.
         */

        // ambiguous(10, 10);


        // =====================================================
        // CASE 7: null ambiguity
        // =====================================================

        /*
         * Uncomment this call:
         *
         * nullTest(null);
         *
         * If both String and Integer methods exist, the compiler
         * cannot determine which one to call.
         */
    }


    // Methods used for demonstrating ambiguity

    static void ambiguous(Integer a, Double b) {
        System.out.println("Integer, Double");
    }

    static void ambiguous(Double a, Integer b) {
        System.out.println("Double, Integer");
    }


    static void nullTest(String s) {
        System.out.println("String");
    }

    static void nullTest(Integer i) {
        System.out.println("Integer");
    }
}
