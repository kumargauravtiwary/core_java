package com.collection.dsa;

public class EqualsVsDoubleEquals {

    public static void main(String[] args) {

        // ==========================================
        // PART 1: STRING COMPARISON
        // ==========================================
        System.out.println("========== STRING COMPARISON ==========");

        // String Literals -> Stored in the String Pool (Interned)
        String s1 = "Hello";
        String s2 = "Hello";

        // String Objects -> Explicitly created on the Heap
        String s3 = new String("Hello");
        String s4 = new String("Hello");

        // 1. Comparing literals
        System.out.println("s1 == s2 (both literals): " + (s1 == s2)); 
        // true (Both point to the SAME memory address in the String Pool)

        System.out.println("s1.equals(s2): " + s1.equals(s2)); 
        // true (Content is identical)

        // 2. Comparing literal vs new Object
        System.out.println("\ns1 == s3 (literal vs new): " + (s1 == s3)); 
        // false (Different memory addresses: Pool vs Heap)

        System.out.println("s1.equals(s3): " + s1.equals(s3)); 
        // true (Content is still "Hello")

        // 3. Comparing two new Objects
        System.out.println("\ns3 == s4 (new vs new): " + (s3 == s4)); 
        // false (Two distinct objects on the Heap, different addresses)

        System.out.println("s3.equals(s4): " + s3.equals(s4)); 
        // true (Overridden equals() compares characters sequentially)

        // ==========================================
        // PART 2: WRAPPER CLASS (Integer) COMPARISON
        // ==========================================
        System.out.println("\n========== WRAPPER (Integer) COMPARISON ==========");

        // Case A: Values within the Integer Cache range (-128 to 127)
        Integer i1 = 100;
        Integer i2 = 100;

        // Case B: Values outside the Integer Cache range
        Integer i3 = 200;
        Integer i4 = 200;

        // Case C: Explicitly forcing new Heap objects
        Integer i5 = new Integer(100);
        Integer i6 = new Integer(100);

        // 1. Cached values
        System.out.println("i1 == i2 (100 cached): " + (i1 == i2)); 
        // true (Java reuses the same cached Integer object for values -128 to 127)

        System.out.println("i1.equals(i2): " + i1.equals(i2)); 
        // true (Compares the int value)

        // 2. Non-cached values
        System.out.println("\ni3 == i4 (200 non-cached): " + (i3 == i4)); 
        // false (200 is outside the cache, so Java creates two separate objects)

        System.out.println("i3.equals(i4): " + i3.equals(i4)); 
        // true (Compares the int value, which is 200 == 200)

        // 3. Explicit 'new' objects (even within cache range)
        System.out.println("\ni5 == i6 (new Integer(100)): " + (i5 == i6)); 
        // false ('new' forces a fresh heap object regardless of cache)

        System.out.println("i5.equals(i6): " + i5.equals(i6)); 
        // true (Compares the int value)

        // ==========================================
        // BONUS: Mixing Primitive and Wrapper
        // ==========================================
        System.out.println("\n========== PRIMITIVE vs WRAPPER (Bonus) ==========");
        int primitive = 200;
        
        System.out.println("i3 == primitive (200 == 200): " + (i3 == primitive)); 
        // true (When a Wrapper meets a primitive, Java unboxes i3 to int)
    }
}
