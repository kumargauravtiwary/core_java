package Slidingwindow;
//Find the maximum sum of a subarray of size K using a sliding window.
public class MaxSumSubarray1 {   
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 2;
        int maxSum = maxSumSubarray(arr, k);
        System.out.println("Maximum sum of subarray of size " + k + " is: " + maxSum);
    }

    private static int maxSumSubarray(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            return -1; // Invalid input
        }
        int maxSum = 0;
        for (int i = 0; i < k; i++) {
            maxSum += arr[i];
        }
        int windowSum = maxSum;
        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i] - arr[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }
}