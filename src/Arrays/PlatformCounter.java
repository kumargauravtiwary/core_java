package Arrays;

import java.util.Arrays;

public class PlatformCounter {
	
	private static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arrival = {900, 940, 950, 1100, 1500, 1800};
		int[] departure = {910, 1200, 1120, 1130, 1900, 2000};
		
		int result = minPlatforms(arrival, departure);
		System.out.println("Minimum number of platforms required: " + result);
	}
	// Find the minimum number of platforms required for the trains to arrive and depart without delay.
    public static int minPlatforms(int[] arrival, int[] departure) {
        Arrays.sort(arrival);
        Arrays.sort(departure);
        
        int n = arrival.length;
        int i = 0, j = 0;
        int platforms = 0, maxPlatforms = 0;
        
        while (i < n) {
            if (arrival[i] <= departure[j]) {
                platforms++;
                maxPlatforms = Math.max(maxPlatforms, platforms);
                i++;
            } else {
                platforms--;
                j++;
            }
        }
        return maxPlatforms;
    }
}
