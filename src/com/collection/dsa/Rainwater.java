package com.collection.dsa;

public class Rainwater {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Example usage
		int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
		Rainwater solution = new Rainwater();
		int result = solution.trap(height);
		System.out.println(result); // Output: 6
	}
	
	//Find the trapping rainwater problem answer using the two-pointer approach.
	    public int trap(int[] height) {
	        if (height == null || height.length == 0) return 0;
	        
	        int left = 0, right = height.length - 1;
	        int leftMax = 0, rightMax = 0;
	        int water = 0;
	        
	        while (left < right) {
	            if (height[left] < height[right]) {
	                if (height[left] >= leftMax) {
	                    leftMax = height[left];
	                } else {
	                    water += leftMax - height[left];
	                }
	                left++;
	            } else {
	                if (height[right] >= rightMax) {
	                    rightMax = height[right];
	                } else {
	                    water += rightMax - height[right];
	                }
	                right--;
	            }
	        }
	        return water;
	    }
}


