package Arrays;
import java.util.*;
public class threesome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Example usage
		int[] nums = {-1, 0, 1, 2, -1, -4};
		threesome solution = new threesome();
		List<List<Integer>> result = solution.threeSum(nums);
		System.out.println(result); // Output: [[-1, -1, 2], [-1, 0, 1]] 

	}
		    public List<List<Integer>> threeSum(int[] nums) {
	        Arrays.sort(nums);
	        List<List<Integer>> result = new ArrayList<>();
	        int n = nums.length;
	        
	        for (int i = 0; i < n - 2; i++) {
	            // Skip duplicate fixed elements
	            if (i > 0 && nums[i] == nums[i-1]) continue;
	            // Optimization
	            if (nums[i] > 0) break;
	            
	            int left = i + 1, right = n - 1;
	            int target = -nums[i];
	            
	            while (left < right) {
	                int sum = nums[left] + nums[right];
	                if (sum == target) {
	                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
	                    // Skip duplicates
	                    left++;
	                    right--;
	                    while (left < right && nums[left] == nums[left - 1]) left++;
	                    while (left < right && nums[right] == nums[right + 1]) right--;
	                } else if (sum < target) {
	                    left++;
	                } else {
	                    right--;
	                }
	            }
	        }
	        return result;
	    }
	}


