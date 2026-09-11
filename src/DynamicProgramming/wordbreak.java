package DynamicProgramming;
//Solve the word break problem using DP (string segmentation into dictionary words).
public class wordbreak {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String s = "leetcode";
        String[] wordDict = {"leet", "code"};
        System.out.println("Can the string be segmented into dictionary words? " + wordBreak(s, wordDict));
    }
    
    // Function to check if the string can be segmented into dictionary words
    public static boolean wordBreak(String s, String[] wordDict) {
        if (s == null || s.length() == 0) return true;
        
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // Empty string can always be segmented
        
        for (int i = 1; i <= n; i++) {
            for (String word : wordDict) {
                int len = word.length();
                if (i >= len && dp[i - len] && s.substring(i - len, i).equals(word)) {
                    dp[i] = true;
                    break;
                }
            }
            System.out.println("dp[" + i + "] = " + dp[i]);
        }
        
        return dp[n];
    }
}
