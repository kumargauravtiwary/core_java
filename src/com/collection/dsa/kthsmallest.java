package com.collection.dsa;

import java.util.concurrent.ThreadLocalRandom;

public class kthsmallest {
	
	public static int findKthSmallest(int[] arr, int k) {
		if (k < 1 || k > arr.length) {
			throw new IllegalArgumentException("k is out of bounds");
		}
		return quickselect(arr, 0, arr.length - 1, k - 1); // Convert to 0-based index
	}
	 
    private static int partition(int[] arr, int low, int high) {
        // Random pivot
        int randIdx = ThreadLocalRandom.current().nextInt(low, high + 1);
        swap(arr, randIdx, high);
        
        int pivot = arr[high];
        int i = low;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, high);
        return i;
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static int quickselect(int[] arr, int low, int high, int k) { // 0-based k
        if (low == high) return arr[low];
        
        int pivotIdx = partition(arr, low, high);
        
        if (k == pivotIdx) return arr[k];
        else if (k < pivotIdx) return quickselect(arr, low, pivotIdx - 1, k);
        else return quickselect(arr, pivotIdx + 1, high, k);
    }
}