package Arrays;
//Find the Kth smallest and Kth largest element without full sorting.
import java.util.*;

public class KthElement {

    public static int kthSmallest(int[] nums, int k) {
        if (k < 1 || k > nums.length) {
            throw new IllegalArgumentException("Invalid k");
        }

        return quickSelect(nums, 0, nums.length - 1, k - 1);
    }

    public static int kthLargest(int[] nums, int k) {
        if (k < 1 || k > nums.length) {
            throw new IllegalArgumentException("Invalid k");
        }

        return quickSelect(nums, 0, nums.length - 1,
                           nums.length - k);
    }

    private static int quickSelect(
            int[] nums, int left, int right, int targetIndex) {

        while (left <= right) {

            int pivotIndex = partition(nums, left, right);
            // Check if the pivot index is the target index
            if (pivotIndex == targetIndex) {
                return nums[pivotIndex];
            }
            // If the pivot index is less than the target index, search in the right subarray
            if (pivotIndex < targetIndex) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }

        throw new IllegalStateException();
    }

    private static int partition(
            int[] nums, int left, int right) {

        int pivot = nums[right];
        int i = left;

        for (int j = left; j < right; j++) {

            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }

        swap(nums, i, right);

        return i;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {

        int[] nums = {7, 10, 4, 3, 20, 15};

        int k = 3;

        System.out.println(
                "3rd Smallest = " +
                kthSmallest(nums.clone(), k)
        );

        System.out.println(
                "3rd Largest = " +
                kthLargest(nums.clone(), k)
        );
    }
}
