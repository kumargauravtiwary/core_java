package Strings;

import java.util.*;

public class UniquePermutations {

    public static void permutations(String str) {
        Map<Character, Integer> frequency = new TreeMap<>();

        for (char c : str.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        backtrack(frequency, new StringBuilder(), str.length());
    }

    private static void backtrack(
            Map<Character, Integer> frequency,
            StringBuilder current,
            int length) {

        if (current.length() == length) {
            System.out.println(current);
            return;
        }

        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {

            char c = entry.getKey();
            int count = entry.getValue();

            if (count == 0) {
                continue;
            }

            // Choose
            current.append(c);
            frequency.put(c, count - 1);

            // Explore
            backtrack(frequency, current, length);

            // Undo
            frequency.put(c, count);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        permutations("AAB");
    }
}
