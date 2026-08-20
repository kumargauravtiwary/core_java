package com.collection.dsa;

public class Leaders {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//call the findLeaders method with an example array
		int arr[] = {16, 17, 4, 3, 5, 2};
		Leaders leaders = new Leaders();
		System.out.println("Leaders in the array: ");
		leaders.findLeaders(arr);
	}
	//Find the leaders in an array (elements greater than all elements to their right).
	public void findLeaders(int arr[]) {
		int n = arr.length;
		int maxFromRight = arr[n - 1];
		System.out.print(maxFromRight + " ");
		for (int i = n - 2; i >= 0; i--) {
			if (arr[i] > maxFromRight) {
				maxFromRight = arr[i];
				System.out.print(maxFromRight + " ");
			}
		}
	}

}
