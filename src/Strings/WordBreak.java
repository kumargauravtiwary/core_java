package Strings;

import java.util.*;

public class WordBreak {

    public static boolean wordBreak(
            String s,
            List<String> wordDict) {

        Set<String> dictionary = new HashSet<>(wordDict);
        System.out.println("Dictionary: " + dictionary);
        int n = s.length();

        // dp[i] = true if s[0...i-1] can be segmented
        boolean[] dp = new boolean[n + 1];

        // Empty string can always be segmented
        dp[0] = true;
        System.out.println("Initial DP Array:");
        printDP(dp);

        for (int i = 1; i <= n; i++) {

            for (int j = 0; j < i; j++) {

                if (dp[j] &&
                    dictionary.contains(s.substring(j, i))) {

                    dp[i] = true;
                    break;
                }
            }
        }
        // Print the final DP array
        System.out.println("Final DP Array:"); 
        printDP(dp);
        return dp[n];
    }
    //functiopn to print dp array
    private static void printDP(boolean[] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        System.out.println(
            wordBreak(
                "leetcode",
                Arrays.asList("leet", "code")
            )
        ); // true

        System.out.println(
            wordBreak(
                "applepenapple",
                Arrays.asList("apple", "pen")
            )
        ); // true

        System.out.println(
            wordBreak(
                "catsandog",
                Arrays.asList(
                    "cats", "dog", "sand",
                    "and", "cat"
                )
            )
        ); // false
    }
}
