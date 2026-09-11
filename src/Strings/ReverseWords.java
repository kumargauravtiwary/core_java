package Strings;

public class ReverseWords {

    public static void reverseWords(char[] s) {

        // Step 1: Reverse the entire array
        reverse(s, 0, s.length - 1);

        // Step 2: Reverse each word
        int start = 0;

        for (int i = 0; i <= s.length; i++) {

            // Word ends at space or end of array
            if (i == s.length || s[i] == ' ') {

                reverse(s, start, i - 1);

                start = i + 1;
            }
        }
    }

    private static void reverse(char[] s, int left, int right) {

        while (left < right) {

            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            left++;
            right--;
        }
    }

    public static void main(String[] args) {

        char[] sentence = "I love Java".toCharArray();

        reverseWords(sentence);

        System.out.println(new String(sentence));
    }
}
