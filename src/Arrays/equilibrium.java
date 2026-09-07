package Arrays;

public class equilibrium {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		equilibrium eq = new equilibrium();
		int arr[] = { -7, 1, 5, 2, -4, 3, 0 };
		int index = eq.findEquilibriumIndex(arr);
		if (index != -1) {
			System.out.println("Equilibrium index is: " + index);
		} else {
			System.out.println("No equilibrium index found.");
		}
		

	}
	//Find the equilibrium index of an array (left sum equals right sum).
	public int findEquilibriumIndex(int arr[]) {
		int n = arr.length;
		int totalSum = 0;
		for (int i = 0; i < n; i++) {
			totalSum += arr[i];
		}
		int leftSum = 0;
		for (int i = 0; i < n; i++) {
			totalSum -= arr[i];
			if (leftSum == totalSum) {
				return i;
			}
			leftSum += arr[i];
		}
		return -1;
	}

}
