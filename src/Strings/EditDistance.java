package Strings;

//Find the edit distance (Levenshtein distance) between two strings using DP.
public class EditDistance {

    public static int editDistance(String s1, String s2) {

        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];

        // Transform empty string into s2
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Transform s1 into empty string
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        System.out.println("Initial DP Array:");
        printDP(dp);
        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {

                    // Characters match
                    dp[i][j] = dp[i - 1][j - 1];

                } else {

                    // Insert, delete, or replace
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j],                 // Delete
                            Math.min(
                                    dp[i][j - 1],         // Insert
                                    dp[i - 1][j - 1]      // Replace
                            )
                    );
                }
            }
        }
        System.out.println("Final DP Array:");
        printDP(dp);
        return dp[m][n];
    }
    //function to print dp array
    private static void printDP(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        System.out.println(
            editDistance("kitten", "sitting")
        ); // 3

        System.out.println(
            editDistance("horse", "ros")
        ); // 3

        System.out.println(
            editDistance("abc", "abc")
        ); // 0
    }
}
