package Arrays;

import java.util.*;

public class MinimumSwaps {

    public static int minSwaps(int[] arr) {
        int n = arr.length;

        // Store {value, originalIndex}
        int[][] pairs = new int[n][2];

        for (int i = 0; i < n; i++) {
            pairs[i][0] = arr[i];
            pairs[i][1] = i;
        }

        // Sort by value
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));

        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {

            // Already visited or already in correct position
            if (visited[i] || pairs[i][1] == i) {
                continue;
            }

            int cycleSize = 0;
            int j = i;

            // Find cycle
            while (!visited[j]) {
                visited[j] = true;
                j = pairs[j][1];
                cycleSize++;
            }

            // Cycle of size k requires k-1 swaps
            swaps += cycleSize - 1;
        }

        return swaps;
    }

    public static void main(String[] args) {

        int[] arr = {1, 5, 4, 3, 2};

        System.out.println("Minimum swaps = " + minSwaps(arr));
    }
}
