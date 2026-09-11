package ArrayMatrix;

public class smallestSubarrayWithSum {
    //Find the smallest subarray with a sum greater than or equal to a given value.
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 2, 3, 2};
        int targetSum = 7;
        int minLength = smallestSubarrayWithSum(arr, targetSum);
        System.out.println("Smallest subarray length with sum >= " + targetSum + " is: " + minLength);
    }
    //Find the smallest subarray with a sum greater than or equal to a given value.
    private static int smallestSubarrayWithSum(int[] arr, int targetSum) {
        int minLength = Integer.MAX_VALUE;
        int windowSum = 0;
        int windowStart = 0;
        // Expand the window by moving windowEnd to the right
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            // Add the right element to the window sum
            windowSum += arr[windowEnd];
            // Shrink the window from the left while the window sum is greater than or equal to the target sum
            while (windowSum >= targetSum) {
                // Update the minimum length of the subarray found so far
                minLength = Math.min(minLength, windowEnd - windowStart + 1);
                // Remove the left element from the window sum
                windowSum -= arr[windowStart];
                // Move the window start to the right
                windowStart++;
            }
        }
        // If no valid subarray was found, return 0; otherwise, return the minimum length
        return (minLength == Integer.MAX_VALUE) ? 0 : minLength;
    }
}
