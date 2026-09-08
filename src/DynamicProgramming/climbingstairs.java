package DynamicProgramming;

//Solve the climbing stairs problem (count distinct ways to reach the top).
public class climbingstairs {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int n = 5;
        System.out.println("Number of distinct ways to climb " + n + " stairs: " + climbStairs(n));
    }
    
    // Function to count the number of distinct ways to reach the top of the stairs
    public static int climbStairs(int n) {
        if (n <= 1) return 1;
        
        int[] dp = new int[n + 1];
        dp[0] = 1; // 1 way to stay at the ground (do nothing)
        dp[1] = 1; // 1 way to reach the first step
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2]; // The number of ways to reach step i is the sum of ways to reach step i-1 and step i-2
        }
        
        return dp[n];
    }
}
