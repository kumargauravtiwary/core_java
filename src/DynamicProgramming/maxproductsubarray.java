package DynamicProgramming;
//Solve the maximum product subarray problem using DP
public class maxproductsubarray {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] nums = {2, 3, -2, 4};
        System.out.println("Maximum product subarray: " + maxProduct(nums));
    }
    
    // Function to find the maximum product subarray
    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        int maxProduct = nums[0];
        int minProduct = nums[0];
        int result = nums[0];
        
        for (int i = 1; i < n; i++) {
            if (nums[i] < 0) {
                // Swap maxProduct and minProduct when encountering a negative number
                int temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }
            maxProduct = Math.max(nums[i], maxProduct * nums[i]);
            minProduct = Math.min(nums[i], minProduct * nums[i]);
            result = Math.max(result, maxProduct);
            System.out.println("maxProduct[" + i + "] = " + maxProduct + ", minProduct[" + i + "] = " + minProduct + ", result = " + result);
        }
        
        return result;
    }
}
