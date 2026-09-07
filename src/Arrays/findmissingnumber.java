package Arrays;

public class findmissingnumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {1,2,4,5,6,7,8,9};
		System.out.println(missingNumber(arr));
		

	}
    //Find the missing number in an array containing numbers from 1 to N with one number missing.
	public static int missingNumber(int[] nums) {
        int n = nums.length + 1;
        int missing = 0;
        // XOR all numbers from 1 to N and the elements in the array to find the missing number
        for (int i = 0; i < nums.length; i++) {
            missing ^= (i + 1);   // XOR full range (1 to N)
            missing ^= nums[i];   // XOR array element
        }
        
        return missing;
    }

}
