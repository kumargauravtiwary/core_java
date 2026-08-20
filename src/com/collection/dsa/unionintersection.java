package com.collection.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class unionintersection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr1 = {1, 2, 4, 5, 6};
		int[] arr2 = {2, 3, 5, 7};
		
		List<List<Integer>> result = unionIntersection(arr1, arr2);
		System.out.println("Union: " + result.get(0));
		System.out.println("Intersection: " + result.get(1));

	}
	//Find the union and intersection of two unsorted arrays.
	public static List<List<Integer>> unionIntersection(int[] arr1, int[] arr2) {
        Set<Integer> set1 = new HashSet<>();
        for (int x : arr1) set1.add(x);
        
        Set<Integer> union = new HashSet<>(set1);
        for (int x : arr2) union.add(x);
        
        Set<Integer> intersection = new HashSet<>();
        for (int x : arr2) {
            if (set1.contains(x)) {
                intersection.add(x);
            }
        }
        
        return Arrays.asList(new ArrayList<>(union), new ArrayList<>(intersection));
    }
	

}
