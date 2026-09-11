package Strings;

import java.util.*;

public class Permutations {

    public static void permutations(String str) {
        backtrack(str.toCharArray(), 0);
    }

    private static void backtrack(char[] chars, int index) {
        if (index == chars.length) {
            System.out.println(new String(chars));
            return;
        }

        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);

            backtrack(chars, index + 1);

            // Backtrack
            swap(chars, index, i);
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        permutations("ABC");
    }
}
