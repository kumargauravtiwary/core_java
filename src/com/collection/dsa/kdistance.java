package com.collection.dsa;
//give proper comments before each line to understand the code
public class kdistance {
    //Find the longest substring with at most K distinct characters.
    public static void main(String[] args) {
        String s = "araaci";
        int k = 2;
        int maxLength = longestSubstringWithKDistinctChars(s, k);
        System.out.println("Longest substring length with at most " + k + " distinct characters is: " + maxLength);
    }
    //Find the longest substring with at most K distinct characters.
    private static int longestSubstringWithKDistinctChars(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) {
            return 0;
        }
        // Create a map to store the frequency of characters in the current window
        java.util.Map<Character, Integer> charFrequency = new java.util.HashMap<>();
        int windowStart = 0;
        int maxLength = 0;
        // Expand the window by moving windowEnd to the right
        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char rightChar = s.charAt(windowEnd);
            // Add the right character to the frequency map
            charFrequency.put(rightChar, charFrequency.getOrDefault(rightChar, 0) + 1);
            // Shrink the window from the left if the number of distinct characters exceeds k    
            while (charFrequency.size() > k) {
                // Remove the left character from the frequency map
                char leftChar = s.charAt(windowStart);
                charFrequency.put(leftChar, charFrequency.get(leftChar) - 1);
                // If the frequency of the left character becomes 0, remove it from the map
                if (charFrequency.get(leftChar) == 0) {
                    charFrequency.remove(leftChar);
                }
                windowStart++;
            }
            // Update the maximum length of the substring found so far
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }
}
