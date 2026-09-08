package DynamicProgramming;
//Solve the house robber II problem (circular array variant).
public class houserobber2 {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] nums = {2, 3, 2};
        System.out.println("Maximum sum of non-adjacent elements in circular array: " + rob(nums));
    }
    
    // Function to find the maximum sum of non-adjacent elements in a circular array
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        // Rob houses excluding the first house
        int max1 = robLinear(nums, 1, nums.length - 1);
        // Rob houses excluding the last house
        int max2 = robLinear(nums, 0, nums.length - 2);
        
        return Math.max(max1, max2);
    }
    
    // Helper function to perform the linear house robber problem
    private static int robLinear(int[] nums, int start, int end) {
        if (start == end) return nums[start];
        
        int prev1 = nums[start];
        int prev2 = Math.max(nums[start], nums[start + 1]);
        
        for (int i = start + 2; i <= end; i++) {
            int current = Math.max(prev2, prev1 + nums[i]);
            prev1 = prev2;
            prev2 = current;
        }
        
        return prev2;
    }
}
