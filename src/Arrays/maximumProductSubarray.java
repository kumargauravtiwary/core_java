package Arrays;

public class maximumProductSubarray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//call the maxProduct method with an example array
		int arr[] = {2,3,-2,4};//maximum product subarray is [2,3] with product 6
		maximumProductSubarray mps = new maximumProductSubarray();
		int maxProduct = mps.maxProduct(arr);
		System.out.println("Maximum product subarray: " + maxProduct);
		

	}
	//Find the maximum product subarray.
	public int maxProduct(int[] nums) {
	    if (nums == null || nums.length == 0) {
	        return 0;
	    }
	    
	    int maxProduct = nums[0];
	    int minProduct = nums[0];
	    int result = nums[0];
	    
	    for (int i = 1; i < nums.length; i++) {
	        if (nums[i] < 0) {
	            // Swap max and min when a negative number is encountered
	            int temp = maxProduct;
	            maxProduct = minProduct;
	            minProduct = temp;
	        }
	        
	        maxProduct = Math.max(nums[i], maxProduct * nums[i]);
	        minProduct = Math.min(nums[i], minProduct * nums[i]);
	        
	        result = Math.max(result, maxProduct);
	    }
	    
	    return result;
	}

}
