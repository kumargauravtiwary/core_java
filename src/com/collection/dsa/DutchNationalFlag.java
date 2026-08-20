package com.collection.dsa;

public class DutchNationalFlag {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {2, 0, 2, 1, 1, 0};
        sort012(arr);
        for (int num : arr) System.out.print(num + " ");
        // Output: 0 0 1 1 2 2
	}
	//Sort an array of 0s, 1s, and 2s in one pass (Dutch National Flag problem).
	public static void sort012(int[] arr) {
        int low = 0, mid = 0, high = arr.length - 1;
        while (mid <= high) {
            switch (arr[mid]) {
                case 0:
                    // swap arr[low] and arr[mid]
                    int temp0 = arr[low];
                    arr[low] = arr[mid];
                    arr[mid] = temp0;
                    low++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    // swap arr[mid] and arr[high]
                    int temp2 = arr[high];
                    arr[high] = arr[mid];
                    arr[mid] = temp2;
                    high--;
                    break;
            }
        }
	
	}
}
