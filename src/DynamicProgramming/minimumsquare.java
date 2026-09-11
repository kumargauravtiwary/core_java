package DynamicProgramming;
//Solve the perfect squares problem (minimum number of perfect squares summing toN).
public class minimumsquare {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int n = 12;
        System.out.println("Minimum number of perfect squares summing to " + n + ": " + numSquares(n));
    }
    
    // Function to find the minimum number of perfect squares summing to N
    public static int numSquares(int n) {
        if (n <= 0) return 0;
        
        int[] dp = new int[n + 1];
        // Initialize the dp array with a value greater than n
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        
        dp[0] = 0; // Base case: 0 perfect squares sum to 0
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                System.out.println("dp[" + i + "] = " + dp[i] + " (using square " + (j * j) + ")");
            }
        }
        
        return dp[n];
    }
}
