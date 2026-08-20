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

    private static void findPermutations(String str, String prefix, ArrayList<String> result) {
        if (str.length() == 0) {
            result.add(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                String rem = str.substring(0, i) + str.substring(i + 1);
                findPermutations(rem, prefix + str.charAt(i), result);
            }
        }
    }
}