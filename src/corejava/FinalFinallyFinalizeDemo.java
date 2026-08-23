package corejava;
public class FinalFinallyFinalizeDemo {

    public static void main(String[] args) {

        // =====================================================
        // 1. final
        // =====================================================

        // final variable -> value cannot be changed
        final int MAX_VALUE = 100;

        System.out.println("Final variable: " + MAX_VALUE);

        // Uncommenting this will cause compilation error:
        // MAX_VALUE = 200;


        // final method demonstration
        Student student = new Student();
        student.display();


        // =====================================================
        // 2. finally
        // =====================================================

        try {
            System.out.println("\nInside try block");

            int result = 10 / 0;

            System.out.println(result);

        } catch (ArithmeticException e) {
            System.out.println("Exception caught: " + e.getMessage());

        } finally {
            System.out.println("Finally block always executes");
        }


        // =====================================================
        // 3. finalize()
        // =====================================================

        Student s1 = new Student();

        // Remove the reference
        s1 = null;

        // Request JVM to perform garbage collection
        System.gc();

        System.out.println("\nEnd of main()");
    }
}


// =========================================================
// Student class
// =========================================================

class Student {

    // final variable
    final int id = 101;


    // final method
    final void display() {
        System.out.println("Final method executed");
    }


    // finalize() demonstration
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize() called before object is garbage collected");

        super.finalize();
    }
}

