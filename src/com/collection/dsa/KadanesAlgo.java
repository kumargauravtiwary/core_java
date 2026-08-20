package com.collection.dsa;

public class KadanesAlgo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
	        int[] result = kadaneWithIndices(arr);
	        System.out.println("Maximum subarray sum: " + result[0]);
	        System.out.println("Start index: " + result[1]);
	        System.out.println("End index: " + result[2]);
		

	}
	    public static int[] kadaneWithIndices(int[] arr) {
	        if (arr == null || arr.length == 0) {
	            return new int[]{0, -1, -1};
	        }

	        int currSum = arr[0];
	        int bestSum = arr[0];
	        int start = 0, end = 0;
	        int tempStart = 0;

	        for (int i = 1; i < arr.length; i++) {
	            if (currSum < 0) {
	                currSum = arr[i];
	                tempStart = i;
	            } else {
	                currSum += arr[i];
	            }

	            if (currSum > bestSum) {
	                bestSum = currSum;
	                start = tempStart;
	                end = i;
	            }
	        }

	        return new int[]{bestSum, start, end};
	    
	}

}
