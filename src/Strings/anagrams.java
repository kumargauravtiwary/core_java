package Strings;

public class anagrams {
    //Check if two strings are anagrams of each other.
    public static void main(String[] args) {
        String str1 = "listen";
        String str2 = "silent";
        boolean result = areAnagrams(str1, str2);
        if (result) {
            System.out.println(str1 + " and " + str2 + " are anagrams.");
        } else {
            System.out.println(str1 + " and " + str2 + " are not anagrams.");
        }
    }
    //Check if two strings are anagrams of each other.
    public static boolean areAnagrams(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }

        int[] charCount = new int[256]; // Assuming ASCII characters

        // Count the occurrences of each character in the first string
        for (char c : str1.toCharArray()) {
            charCount[c]++;
        }

        // Subtract the occurrences of each character in the second string
        for (char c : str2.toCharArray()) {
            charCount[c]--;
            if (charCount[c] < 0) {
                return false; // More occurrences in str2 than in str1
            }
        }

        return true; // All character counts are zero, so they are anagrams
    }
}
