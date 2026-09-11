package Strings;
//Find the minimum number of insertions to make a string a palindrome
public class PalindromeInsertions {

    public static int minInsertions(String s) {
        System.out.println("Input string: " + s);
        int n = s.length();

        if (n <= 1) {
            return 0;
        }

        int[][] dp = new int[n][n];

        // dp[i][j] = minimum insertions
        // needed to make s[i...j] a palindrome

        for (int len = 2; len <= n; len++) {

            for (int i = 0; i + len - 1 < n; i++) {

                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j)) {
                    // If the characters at both ends are the same, no insertions are needed
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    // If the characters at both ends are different, we need to insert one character
                    // and take the minimum of the two possible subproblems
                    dp[i][j] = 1 + Math.min(
                            dp[i + 1][j],
                            dp[i][j - 1]
                    );
                }
                System.out.println("dp[" + i + "][" + j + "] = " + dp[i][j]);
            }
        }
        //print entire dp array in for loop
        System.out.println("DP Array:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(minInsertions("abcda")); // 2
        System.out.println(minInsertions("race"));  // 3
        System.out.println(minInsertions("aba"));   // 0
        System.out.println(minInsertions("abc"));   // 2
    }
}
