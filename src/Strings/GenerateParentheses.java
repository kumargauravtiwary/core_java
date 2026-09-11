package Strings;

import java.util.*;

public class GenerateParentheses {

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        backtrack(
                result,
                new StringBuilder(),
                0,  // open
                0,  // close
                n
        );

        return result;
    }

    private static void backtrack(
            List<String> result,
            StringBuilder current,
            int open,
            int close,
            int n) {

        // Complete valid combination
        if (current.length() == 2 * n) {
            result.add(current.toString());
            return;
        }

        // Add '(' if we still have opening brackets available
        if (open < n) {
            current.append('(');

            backtrack(
                    result,
                    current,
                    open + 1,
                    close,
                    n
            );

            // Backtrack
            current.deleteCharAt(current.length() - 1);
        }

        // Add ')' only when it won't make the sequence invalid
        if (close < open) {
            current.append(')');

            backtrack(
                    result,
                    current,
                    open,
                    close + 1,
                    n
            );

            // Backtrack
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {

        List<String> result = generateParenthesis(3);

        for (String s : result) {
            System.out.println(s);
        }
    }
}
