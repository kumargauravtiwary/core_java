package corejava;

//Find all anagrams of a pattern string within a larger string using a sliding window
public class Anagrams {
    public static void main(String[] args) {
        String str = "cbaebabacd";
        String pattern = "abc";
        findAnagrams(str, pattern);
    }

    // Method to find all anagrams of a pattern string within a larger string
    public static void findAnagrams(String str, String pattern) {
        int[] charCount = new int[26];
        // Count the frequency of each character in the pattern
        for (char c : pattern.toCharArray()) {
            charCount[c - 'a']++;
            System.out.println(charCount[c - 'a']);
        }
        
        int start = 0, end = 0, count = pattern.length();
        // Use a sliding window to find anagrams
        while (end < str.length()) {
            // If the character at the end of the window is part of the pattern, decrease the count
            if (charCount[str.charAt(end) - 'a'] > 0) {
                count--;
            }
            // Decrease the count of the character at the end of the window
            charCount[str.charAt(end) - 'a']--;
            end++;

            if (count == 0) {
                System.out.println("Anagram found at index: " + start);
            }
            // If the window size is equal to the pattern length, slide the window
            if (end - start == pattern.length()) {
                // If the character at the start of the window is part of the pattern, increase the count
                if (charCount[str.charAt(start) - 'a'] >= 0) {
                    count++;
                }
                // Increase the count of the character at the start of the window
                charCount[str.charAt(start) - 'a']++;
                start++;
            }
        }
    }
}
