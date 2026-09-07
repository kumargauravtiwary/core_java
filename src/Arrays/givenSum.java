package Arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class givenSum {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {1,1,2,3,3,4,4,5,5};
		int target = 6;
		//call the method to find all pairs that sum to the target
		givenSum gs = new givenSum();
		List<int[]> pairs = gs.allIndexPairs(arr, target);
		//print the pairs
		for (int[] pair : pairs) {
		    System.out.println("Pair: (" + arr[pair[0]] + ", " + arr[pair[1]] + ")");
		}
	}
	//Find all pairs in an array that sum to a given target (two-sum, return all pairs).
	public List<int[]> allIndexPairs(int[] nums, int target) {
	    Map<Integer, List<Integer>> map = new HashMap<>();
	    List<int[]> pairs = new ArrayList<>();
	    // Iterate through the array and find pairs that sum to the target
	    for (int i = 0; i < nums.length; i++) {
	        int comp = target - nums[i];
			// If the complement exists in the map, add the pairs to the result list
	        if (map.containsKey(comp)) {
	            for (int j : map.get(comp)) {
	                pairs.add(new int[]{j, i});
	            }
	        }
	        map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
	    }
	    return pairs;
	}
	

}
