package DynamicProgramming;
//Solve the coin change problem (minimum number of coins to make a target amount).
public class coinchange {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println("Minimum number of coins to make amount " + amount + ": " + coinChange(coins, amount));
    }
    
    // Function to find the minimum number of coins to make a target amount
    public static int coinChange(int[] coins, int amount) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        // Initialize the dp array with a value greater than the amount
        for (int i = 1; i <= amount; i++) {
            dp[i] = max;
        }
        // Base case: 0 coins are needed to make amount 0
        //dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                // Update the dp array with the minimum number of coins needed for each amount
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                System.out.println("dp[" + i + "] = " + dp[i]);
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
