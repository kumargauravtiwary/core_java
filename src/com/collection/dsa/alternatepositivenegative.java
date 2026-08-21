package com.collection.dsa;

public class alternatepositivenegative {

	//psvm
	public static void main(String[] args) {
		
		int arr[] = {1, 2, 3, -4, -1, 4, -5, 6};
		alternatepositivenegative apn = new alternatepositivenegative();
		apn.alternate(arr);
	}

	//method to rearrange the array in alternate positive and negative numbers
	public void alternate(int arr[]) {
		int n = arr.length;
		int posIndex = 0, negIndex = 1;
		// Rearrange the array in alternate positive and negative numbers
		while (posIndex < n && negIndex < n) {
			// If the current positive number is in the correct position, move to the next positive index
			if (arr[posIndex] >= 0) {
				posIndex += 2;
			}// If the current negative number is in the correct position, move to the next negative index 
			else if (arr[negIndex] < 0) {
				negIndex += 2;
			} else {
				// Swap the positive and negative numbers to place them in the correct positions
				int temp = arr[posIndex];
				arr[posIndex] = arr[negIndex];
				arr[negIndex] = temp;
				// Move to the next positive and negative indices
				posIndex += 2;
				negIndex += 2;
			}
		}
		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}
	}
	
	
}
