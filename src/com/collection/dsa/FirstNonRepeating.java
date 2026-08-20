package com.collection.dsa;

public class FirstNonRepeating {
    //Find the first non-repeating character in a string.
    public static void main(String[] args) {
        String str = "swiss";
        char result = firstNonRepeatingCharacter(str);
        if (result != '\0') {
            System.out.println("The first non-repeating character is: " + result);
        } else {
            System.out.println("No non-repeating character found.");
        }
    }
    //Find the first non-repeating character in a string.
    public static char firstNonRepeatingCharacter(String str) {
        if (str == null || str.isEmpty()) {
            return '\0'; // Return null character if the string is empty or null
        }

        int[] charCount = new int[256]; // Assuming ASCII characters

        // Count the occurrences of each character
        for (char c : str.toCharArray()) {
            charCount[c]++;
        }

        // Find the first non-repeating character
        for (char c : str.toCharArray()) {
            if (charCount[c] == 1) {
                return c;
            }
        }

        return '\0'; // Return null character if no non-repeating character is found
    }
}
