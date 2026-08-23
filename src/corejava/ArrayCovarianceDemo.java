package corejava;

public class ArrayCovarianceDemo {

    public static void main(String[] args) {

        // =====================================================
        // 1. Array Covariance
        // =====================================================

        String[] names = {"Alice", "Bob", "Charlie"};

        // String[] IS-A Object[]
        Object[] objects = names;

        System.out.println("Array covariance:");
        System.out.println(objects[0]);
        System.out.println(objects[1]);


        // =====================================================
        // 2. ArrayStoreException
        // =====================================================

        try {

            // The actual array is still String[]
            objects[0] = 100;

        } catch (ArrayStoreException e) {

            System.out.println("\nArrayStoreException caught!");
            System.out.println("Message: " + e.getMessage());
        }


        // =====================================================
        // 3. Valid assignment
        // =====================================================

        objects[0] = "David";

        System.out.println("\nAfter valid assignment:");
        System.out.println(objects[0]);


        // =====================================================
        // 4. Another example
        // =====================================================

        Number[] numbers = new Integer[3];

        try {

            // Integer[] can be referenced by Number[]
            // but the actual array can only contain Integers.

            numbers[0] = 10;       // Valid
            numbers[1] = 20;       // Valid

            numbers[2] = 10.5;     // Invalid

        } catch (ArrayStoreException e) {

            System.out.println("\nArrayStoreException:");
            System.out.println("Cannot store Double in Integer[]");
        }
    }
}
