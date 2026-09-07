package Strings;

public class kmp {
    //Implement the KMP (Knuth-Morris-Pratt) string matching algorithm from scratch.
    public static void main(String[] args) {
        String text = "ababcabcabababd";
        String pattern = "ababd";
        int index = KMPSearch(text, pattern);
        if (index != -1) {
            System.out.println("Pattern found at index: " + index);
        } else {
            System.out.println("Pattern not found in the text.");
        }
    }
    //Implement the KMP (Knuth-Morris-Pratt) string matching algorithm from scratch.
    public static int KMPSearch(String text, String pattern) {
        int[] lps = computeLPSArray(pattern);
        int i = 0; // index for text
        int j = 0; // index for pattern

        while (i < text.length()) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }
            if (j == pattern.length()) {
                return i - j; // Pattern found at index (i - j)
            } else if (i < text.length() && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1]; // Use the LPS array to avoid unnecessary comparisons
                } else {
                    i++;
                }
            }
        }
        return -1; // Pattern not found
    }
    //Compute the LPS (Longest Proper Prefix which is also Suffix) array for the given pattern.
    private static int[] computeLPSArray(String pattern) {
        int n = pattern.length();
        int[] lps = new int[n];
        lps[0] = 0; // LPS of first character is always 0
        int len = 0; // Length of the previous longest prefix suffix
        int i = 1;

        while (i < n) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1]; // Use the previously computed LPS values
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}
