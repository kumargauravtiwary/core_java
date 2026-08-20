package com.collection.dsa;

public class maxSumSubArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {1, 2, 3, 4, 5};
		int k = 2;
		int result = maxSumSubarray(arr, k);
		System.out.println(result); // Output: 9

	}
	public static int maxSumSubarray(int[] arr, int k) {
        int n = arr.length;
        if (n < k || k <= 0) return 0;
        
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        int maxSum = windowSum;
        
        for (int i = k; i < n; i++) {
            windowSum += arr[i] - arr[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }

}
