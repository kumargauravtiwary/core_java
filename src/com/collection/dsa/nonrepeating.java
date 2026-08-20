package com.collection.dsa;

public class nonrepeating {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {1,1,2,3,3,4,4,5,5};
		System.out.println(findSingle(arr));
	}
	public static int findSingle(int[] arr) {
	    int result = 0;
	    for (int num : arr) {
	        result ^= num;
	    }
	    return result;
	}

}
