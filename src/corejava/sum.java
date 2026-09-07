package corejava;

//Find a pair with a given sum in a sorted array using two pointers
public class sum {
    
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 6};
        int target = 6;
        findPairWithSum(arr, target);
    }

    // Method to find a pair with a given sum in a sorted array using two pointers
    public static void findPairWithSum(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int currentSum = arr[left] + arr[right];

            if (currentSum == target) {
                System.out.println("Pair found: (" + arr[left] + ", " + arr[right] + ")");
                return;
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println("No pair found with the given sum.");
    }
}
