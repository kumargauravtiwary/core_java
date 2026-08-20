package com.collection.dsa;

public class makeStringPalindrome {
    //Find the minimum number of insertions to make a string a palindrome.
    public static void main(String[] args) {
        String str = "abc";
        int minInsertions = minInsertionsToMakePalindrome(str);
        System.out.println("Minimum insertions to make the string a palindrome: " + minInsertions);
    }
    //Find the minimum number of insertions to make a string a palindrome.
    private static int minInsertionsToMakePalindrome(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                if (str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[0][n - 1];
    }
}
