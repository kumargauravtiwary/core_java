package Strings;

public class RabinKarp {

    private static final int BASE = 256;
    private static final long MOD = 1_000_000_007L;

    public static int search(String text, String pattern) {

        if (text == null || pattern == null) {
            return -1;
        }

        int n = text.length();
        int m = pattern.length();

        if (m == 0) {
            return 0;
        }

        if (m > n) {
            return -1;
        }

        long patternHash = 0;
        long windowHash = 0;

        // BASE^(m-1) % MOD
        long highestPower = 1;

        for (int i = 0; i < m - 1; i++) {
            highestPower = (highestPower * BASE) % MOD;
        }

        // Initial hash
        for (int i = 0; i < m; i++) {

            patternHash =
                    (patternHash * BASE + pattern.charAt(i)) % MOD;

            windowHash =
                    (windowHash * BASE + text.charAt(i)) % MOD;
        }

        // Slide the window
        for (int i = 0; i <= n - m; i++) {

            // Hashes match → verify characters
            if (patternHash == windowHash) {

                boolean match = true;

                for (int j = 0; j < m; j++) {

                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    return i;
                }
            }

            // Calculate hash for next window
            if (i < n - m) {

                long outgoing =
                        (text.charAt(i) * highestPower) % MOD;

                windowHash =
                        (windowHash - outgoing + MOD) % MOD;

                windowHash =
                        (windowHash * BASE
                                + text.charAt(i + m)) % MOD;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        String text = "hello world";
        String pattern = "world";

        int index = search(text, pattern);

        System.out.println("Pattern found at index: " + index);
    }
}
