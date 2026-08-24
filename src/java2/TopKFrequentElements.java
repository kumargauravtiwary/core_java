package java2;

import java.util.*;

public class TopKFrequentElements {

    public static List<Integer> topKFrequent(
            int[] nums, int k) {

        // Step 1: Count frequency using HashMap
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : nums) {
            frequencyMap.put(
                    num,
                    frequencyMap.getOrDefault(num, 0) + 1
            );
        }

        // Step 2: Min Heap based on frequency
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>(
                        Comparator.comparingInt(Map.Entry::getValue)
                );

        // Step 3: Keep only K most frequent elements
        for (Map.Entry<Integer, Integer> entry :
                frequencyMap.entrySet()) {

            minHeap.offer(entry);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Step 4: Extract result
        List<Integer> result = new ArrayList<>();

        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }

        // Highest frequency first
        Collections.reverse(result);

        return result;
    }

    public static void main(String[] args) {

        int[] nums = {
                1, 1, 1,
                2, 2,
                3,
                4, 4, 4, 4,
                5, 5
        };

        int k = 3;

        List<Integer> result =
                topKFrequent(nums, k);

        System.out.println("Input: "
                + Arrays.toString(nums));

        System.out.println("K: " + k);

        System.out.println("Top " + k
                + " frequent elements: " + result);
    }
}
