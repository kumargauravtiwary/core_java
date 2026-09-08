package DynamicProgramming;
//Solve the house robber problem (maximum sum of non-adjacent elements).
public class houserobber {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] nums = {2, 7, 9, 3, 1};
        System.out.println("Maximum sum of non-adjacent elements: " + rob(nums));
    }
    
    // Function to find the maximum sum of non-adjacent elements
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        int n = nums.length;
        int[] dp = new int[n];
        
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        // Fill the dp array with the maximum sums
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        
        return dp[n - 1];
    }
}
