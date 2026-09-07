package corejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Implement a two-pointer technique solution for the 3Sum problem.
public class threesome {
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        // Iterate through the array, using two pointers to find pairs that sum to the negative of the current element
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length - 1;
            // Use two pointers to find pairs that sum to the negative of the current element
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                // If the sum is zero, add the triplet to the result list and move both pointers to avoid duplicates
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // Move the left pointer to the right while skipping duplicates
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    // Move the right pointer to the left while skipping duplicates
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = threeSum(nums);
        System.out.println(result);
    }
    
}
