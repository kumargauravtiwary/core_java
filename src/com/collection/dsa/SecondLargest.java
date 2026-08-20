package com.collection.dsa;

public class SecondLargest {

    /**
     * Returns the second largest distinct element in the array.
     * Returns Integer.MIN_VALUE if there is no such element.
     */
    public static int findSecondLargest(int[] arr) {
        if (arr == null || arr.length < 2) {
            return Integer.MIN_VALUE; // or throw an exception
        }

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > first) {
                second = first;
                first = num;
            } else if (num > second && num < first) {
                // important: num < first ensures we skip duplicates of the largest
                second = num;
            }
        }

        // If second was never updated, there is no second distinct largest
        return (second == Integer.MIN_VALUE) ? Integer.MIN_VALUE : second;
    }

    public static void main(String[] args) {
        int[] test1 = {3, 1, 4, 4, 2};
        System.out.println(findSecondLargest(test1)); // 3

        int[] test2 = {5, 5, 5, 5};
        System.out.println(findSecondLargest(test2)); // Integer.MIN_VALUE (no second distinct)

        int[] test3 = {10};
        System.out.println(findSecondLargest(test3)); // Integer.MIN_VALUE

        int[] test4 = {2, 2, 1};
        System.out.println(findSecondLargest(test4)); // 1
    }
}