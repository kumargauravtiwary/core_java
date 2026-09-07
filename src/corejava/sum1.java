package corejava;

//Find the smallest subarray with a sum greater than or equal to a given value
public class sum1 {
    
    public static void main(String[] args) {
        int[] arr = {1, 4, 45, 6, 0, 19};
        int target = 51;
        findSmallestSubarrayWithSum(arr, target);
    }

    // Method to find the smallest subarray with a sum greater than or equal to a given value
    public static void findSmallestSubarrayWithSum(int[] arr, int target) {
        int minLength = Integer.MAX_VALUE;
        int currentSum = 0;
        int start = 0;

        for (int end = 0; end < arr.length; end++) {
            currentSum += arr[end];

            while (currentSum >= target) {
                minLength = Math.min(minLength, end - start + 1);
                currentSum -= arr[start];
                start++;
            }
        }

        if (minLength == Integer.MAX_VALUE) {
            System.out.println("No subarray found with the given sum.");
        } else {
            System.out.println("The length of the smallest subarray is: " + minLength);
        }
    }
}
