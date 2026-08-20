package com.collection.dsa;

public class Movezero {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {1,0,3,6,0,9,0,7,0,0,11,56,4};
		moveZeroes(arr);
		for(int a:arr) {
			System.out.println(a);
		}

	}
	public static void moveZeroes(int[] nums) {
        int pos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[pos];
                nums[pos] = nums[i];
                nums[i] = temp;
                pos++;
            }
        }
    }

}
