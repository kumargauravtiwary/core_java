package corejava;

/**
 * Demonstrates final, finally, and finalize().
 */
public class Finalfinally {

    // ---------- 1. 'final' keyword ----------
    // final variable – cannot be reassigned
    private static final double PI = 3.14159;

    // final class – cannot be extended (see below)
    public static final class FinalClass {
        // final method – cannot be overridden in subclasses
        public final void display() {
            System.out.println("This is a final method in a final class.");
        }
    }

    // Attempting to extend FinalClass would cause a compile error:
    // class Child extends FinalClass { } // ❌ not allowed

    // ---------- 2. 'finally' block ----------
    private static void demonstrateFinally() {
        System.out.println("\n--- finally demo ---");
        try {
            System.out.println("Inside try block.");
            int result = 10 / 2;          // normal execution
            System.out.println("Result: " + result);
            // Uncomment next line to see finally with exception
            // int error = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Caught exception: " + e.getMessage());
        } finally {
            System.out.println("finally block always executes (even with or without exception).");
        }

        // finally with return – it still runs before the return
        System.out.println("\n--- finally with return ---");
        try {
            System.out.println("Before return in try.");
            return;   // returning from method
        } finally {
            System.out.println("finally runs even though try has 'return'.");
        }
        // unreachable code here.
    }

    // ---------- 3. 'finalize()' method ----------
    // A class that overrides finalize() – deprecated, but shown for demonstration.
    static class Resource {
        private String name;

        Resource(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            try {
                System.out.println("finalize() called for " + name + " (object ID: " + System.identityHashCode(this) + ")");
                // Cleanup logic (e.g., closing files) – but not recommended.
            } finally {
                super.finalize(); // always call super
            }
        }
    }

    private static void demonstrateFinalize() throws InterruptedException {
        System.out.println("\n--- finalize demo (deprecated) ---");

        // Create objects eligible for GC
        Resource res1 = new Resource("Resource-1");
        Resource res2 = new Resource("Resource-2");

        // Nullify references so they become unreachable
        res1 = null;
        res2 = null;

        // Request garbage collection – finalize() may (or may not) run
        System.out.println("Requesting GC...");
        System.gc();

        // Give GC a little time to run (not guaranteed, but increases chances)
        Thread.sleep(2000);
        System.out.println("End of GC request. (finalize() may have run, but not guaranteed.)");
    }

    // ---------- Main to run all ----------
    public static void main(String[] args) throws InterruptedException {
        // 1. final demonstration
        System.out.println("=== 'final' keyword ===");
        System.out.println("final variable PI = " + PI);
        // PI = 3.14; // ❌ compile error: cannot assign a value to final variable

        FinalClass obj = new FinalClass();
        obj.display(); // final method can be called normally

        // 2. finally demonstration
        demonstrateFinally();

        // 3. finalize demonstration
        demonstrateFinalize();

        System.out.println("\n=== End of demo ===");
    }
}
