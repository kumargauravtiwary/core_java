package recursionandbacktracking;

//import java.uRestoreIPAddressesil.*;
import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {

    public static List<String> restoreIpAddresses(String s) {

        List<String> result = new ArrayList<>();

        backtrack(s, 0, 0, new StringBuilder(), result);

        return result;
    }

    private static void backtrack(
            String s,
            int index,
            int parts,
            StringBuilder current,
            List<String> result) {

        // We have created 4 IP parts
        if (parts == 4) {

            // All digits must have been consumed
            if (index == s.length()) {
                result.add(current.toString());
            }

            return;
        }

        // Try 1, 2, or 3 digits
        for (int length = 1; length <= 3; length++) {

            // Not enough characters
            if (index + length > s.length()) {
                break;
            }

            String part = s.substring(index, index + length);

            // Leading zero is invalid
            if (part.length() > 1 && part.charAt(0) == '0') {
                break;
            }

            // Value must be <= 255
            int value = Integer.parseInt(part);

            if (value > 255) {
                break;
            }

            int oldLength = current.length();

            // Add dot before every part except first
            if (parts > 0) {
                current.append('.');
            }

            current.append(part);

            // Recurse
            backtrack(
                    s,
                    index + length,
                    parts + 1,
                    current,
                    result
            );

            // Backtrack
            current.setLength(oldLength);
        }
    }

    public static void main(String[] args) {

        String s = "25525511135";

        List<String> result =
                restoreIpAddresses(s);

        for (String ip : result) {
            System.out.println(ip);
        }
    }
}
