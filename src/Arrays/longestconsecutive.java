package Arrays;

public class longestconsecutive {

	// Find the longest consecutive sequence in an unsorted array in O(n).
	// Approach: put all numbers in a HashSet. For each number that is the
	// start of a sequence (num-1 not in set), walk forward counting consecutive
	// values. This visits each number at most twice -> O(n) time, O(n) space.
	public static int longestConsecutive(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		java.util.Set<Integer> set = new java.util.HashSet<>();
		for (int n : nums) set.add(n);

		int best = 0;
		// iterate over set to avoid repeated work for duplicates
		for (int n : set) {
			if (!set.contains(n - 1)) { // n is potential sequence start
				int length = 1;
				int cur = n;
				while (set.contains(cur + 1)) {
					cur++;
					length++;
				}
				if (length > best) best = length;
			}
		}
		return best;
	}

	// Simple main with example tests
	public static void main(String[] args) {
		int[][] tests = {
			{100, 4, 200, 1, 3, 2},
			{1, 2, 0, 1},
			{},
			{9},
			{1, 3, 5, 2, 4, 8, 7, 6},
			{-1, 0, 1}
		};

		for (int i = 0; i < tests.length; i++) {
			int res = longestConsecutive(tests[i]);
			System.out.println("Test " + i + " input: " + java.util.Arrays.toString(tests[i]) + " => longest consecutive = " + res);
		}
	}

}

