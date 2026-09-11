package Strings;

public class PalindromicSubstrings {

    public static int countAndPrint(String s) {

        if (s == null || s.isEmpty()) {
            return 0;
        }

        int count = 0;

        for (int center = 0; center < s.length(); center++) {

            // Odd-length palindromes
            count += expandAroundCenter(s, center, center);

            // Even-length palindromes
            count += expandAroundCenter(s, center, center + 1);
        }

        return count;
    }

    private static int expandAroundCenter(
            String s,
            int left,
            int right) {

        int count = 0;

        while (left >= 0 &&
               right < s.length() &&
               s.charAt(left) == s.charAt(right)) {

            System.out.println(
                    s.substring(left, right + 1)
            );

            count++;

            left--;
            right++;
        }

        return count;
    }

    public static void main(String[] args) {

        String s = "ababa";

        int count = countAndPrint(s);

        System.out.println("Total = " + count);
    }
}
