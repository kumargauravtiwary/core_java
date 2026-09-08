package DynamicProgramming;
//Solve the longest increasing subsequence problem using O(n^2) DP and O(n log n)patience sorting.
public class longestsubsequence {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Length of longest increasing subsequence (O(n^2) DP): " + lengthOfLIS_DP(nums));
        System.out.println("Length of longest increasing subsequence (O(n log n) Patience Sorting): " + lengthOfLIS_PatienceSorting(nums));
    }
    
    // Function to find the length of the longest increasing subsequence using O(n^2) DP
    public static int lengthOfLIS_DP(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        int[] dp = new int[n];
        int maxLen = 1;
        
        for (int i = 0; i < n; i++) {
            dp[i] = 1; // Each element is an increasing subsequence of length 1
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    System.out.println("dp[" + i + "] = " + dp[i] + " (nums[" + i + "] = " + nums[i] + ", nums[" + j + "] = " + nums[j] + ")");
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        
        return maxLen;
    }
    
    // Function to find the length of the longest increasing subsequence using O(n log n) Patience Sorting
    public static int lengthOfLIS_PatienceSorting(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int[] tails = new int[nums.length];
        int size = 0;
        
        for (int num : nums) {
            int left = 0, right = size;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            tails[left] = num;
            if (left == size) size++;
        }
        
        return size;
    }
}
