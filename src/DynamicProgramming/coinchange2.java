package DynamicProgramming;
//Solve the coin change II problem (count the number of ways to make a targetamount).
public class coinchange2 {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] coins = {1, 2, 5};
        int amount = 5;
        System.out.println("Number of ways to make amount " + amount + ": " + change(coins, amount));
    }
    
    // Function to count the number of ways to make a target amount
    public static int change(int[] coins, int amount) {
        if (amount < 0) return 0;
        if (amount == 0) return 1;
        
        int[] dp = new int[amount + 1];
        dp[0] = 1; // There is one way to make amount 0 (use no coins)
        
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                // Update the dp array with the number of ways to make each amount
                dp[i] += dp[i - coin];
            }
        }
        
        return dp[amount];
    }
}
