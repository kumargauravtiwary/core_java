package Strings;

public class longestpalindromic {
    //Find the longest palindromic substring using expand-around-center and comparewith DP approach.
    public static void main(String[] args) {
        String s = "babad";
        System.out.println("Longest Palindromic Substring (Expand Around Center): " + longestPalindromeExpand(s));
        System.out.println("Longest Palindromic Substring (Dynamic Programming): " + longestPalindromeDP(s));
    }
    //Find the longest palindromic substring using expand-around-center and comparewith DP approach.
    public static String longestPalindromeExpand(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    //Helper method to expand around the center and find the length of the palindrome.
    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
    //Find the longest palindromic substring using dynamic programming approach.
    public static String longestPalindromeDP(String s) {
        if (s == null || s.length() < 1) return "";
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start = 0, maxLength = 1;

        for (int i = 0; i < n; i++) {
            dp[i][i] = true; // Every single character is a palindrome
        }

        for (int length = 2; length <= n; length++) {
            for (int i = 0; i < n - length + 1; i++) {
                int j = i + length - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (length == 2) {
                        dp[i][j] = true; // Two characters are a palindrome if they are the same
                    } else {
                        dp[i][j] = dp[i + 1][j - 1]; // Check the substring inside
                    }
                } else {
                    dp[i][j] = false;
                }

                if (dp[i][j] && length > maxLength) {
                    start = i;
                    maxLength = length;
                }
            }
        }
        return s.substring(start, start + maxLength);
    }
}
