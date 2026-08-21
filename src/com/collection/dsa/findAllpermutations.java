//Find all permutations of a string (with and without duplicates).
package com.collection.dsa;
import java.util.ArrayList;

class findAllpermutations {
    public static void main(String[] args) {
        String str = "abc";
        ArrayList<String> result = new ArrayList<>();
        findPermutations(str, "", result);
        System.out.println("All permutations of the string are: " + result);
    }
    //Recursive method to find all permutations of a string
    private static void findPermutations(String str, String prefix, ArrayList<String> result) {
        if (str.length() == 0) {
            // If the string is empty, add the prefix to the result list
            result.add(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                // Create a new string by removing the character at index i and adding it to the prefix
                String rem = str.substring(0, i) + str.substring(i + 1);
                // Recursively call the method with the new string and updated prefix
                findPermutations(rem, prefix + str.charAt(i), result);
            }
        }
    }
}