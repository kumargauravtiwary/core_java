package corejava;

import java.util.HashMap;
import java.util.Map;

//Find the maximum number of fruits that can be collected in two baskets (longestsubarray with at most 2 distinct types).
public class longestSubarray {

    public static void main(String[] args) {
        int[] fruits = {1, 2, 1, 2, 3};
        int maxFruits = findMaxFruits(fruits);
        System.out.println("Maximum number of fruits that can be collected: " + maxFruits);
    }

    // Method to find the maximum number of fruits that can be collected in two baskets
    public static int findMaxFruits(int[] fruits) {
        int maxLength = 0;
        int start = 0;
        Map<Integer, Integer> fruitCount = new HashMap<>();
        // Use a sliding window to find the longest subarray with at most 2 distinct types of fruits
        for (int end = 0; end < fruits.length; end++) {
            fruitCount.put(fruits[end], fruitCount.getOrDefault(fruits[end], 0) + 1);
            // If the number of distinct types of fruits exceeds 2, shrink the window from the start
            while (fruitCount.size() > 2) {
                fruitCount.put(fruits[start], fruitCount.get(fruits[start]) - 1);
                if (fruitCount.get(fruits[start]) == 0) {
                    fruitCount.remove(fruits[start]);
                }
                start++;
            }

            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }
}
