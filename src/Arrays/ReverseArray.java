package Arrays;
//Reverse an array in place without using extra space.
public class ReverseArray {

    /**
     * Reverses the given array in place.
     * Time complexity: O(n), Space complexity: O(1)
     */
    public static void reverse(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // nothing to reverse
        }

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            // swap elements at left and right
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            // move pointers towards the center
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] test1 = {1, 2, 3, 4, 5};
        reverse(test1);
        System.out.println(java.util.Arrays.toString(test1)); // [5, 4, 3, 2, 1]

        int[] test2 = {10, 20, 30, 40};
        reverse(test2);
        System.out.println(java.util.Arrays.toString(test2)); // [40, 30, 20, 10]

        int[] test3 = {7};
        reverse(test3);
        System.out.println(java.util.Arrays.toString(test3)); // [7]

        int[] test4 = {};
        reverse(test4);
        System.out.println(java.util.Arrays.toString(test4)); // []
    }
}