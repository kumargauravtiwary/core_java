package Arrays;

public class MaxDifference {

    public static int maxDifference(int[] nums) {

        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException(
                "Array must contain at least 2 elements"
            );
        }

        int minElement = nums[0];
        int maxDifference = nums[1] - nums[0];

        for (int i = 1; i < nums.length; i++) {

            // Larger element must appear AFTER smaller element
            maxDifference = Math.max(
                maxDifference,
                nums[i] - minElement
            );

            // Smallest element seen so far
            minElement = Math.min(
                minElement,
                nums[i]
            );
        }

        return maxDifference;
    }

    public static void main(String[] args) {

        int[] nums = {2, 3, 10, 6, 4, 8, 1};

        System.out.println(maxDifference(nums));
    }
}
