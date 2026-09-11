package Arrays;
//Find all pairs in an array that sum to a given target (two-sum, return all pairs).
import java.util.*;

public class TwoSumAllPairs {

    public static List<List<Integer>> findPairs(int[] nums, int target) {

        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int sum = nums[left] + nums[right];

            if (sum == target) {
                result.add(Arrays.asList(nums[left], nums[right]));

                int leftValue = nums[left];
                int rightValue = nums[right];

                // Skip duplicates
                while (left < right && nums[left] == leftValue) {
                    left++;
                }

                while (left < right && nums[right] == rightValue) {
                    right--;
                }

            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int[] nums = {2, 7, 11, 15, 3, 6, 4, 8, 3};
        int target = 10;

        System.out.println(findPairs(nums, target));
    }
}
