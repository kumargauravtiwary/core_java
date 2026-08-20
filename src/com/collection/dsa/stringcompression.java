package com.collection.dsa;

public class stringcompression {
    //Implement string compression (e.g., "aaabbc" -> "a3b2c1").
    public static void main(String[] args) {
        String input = "aaabbc";
        String compressed = compressString(input);
        System.out.println("Compressed string: " + compressed);
    }
    //Implement string compression (e.g., "aaabbc" -> "a3b2c1").
    public static String compressString(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        StringBuilder compressed = new StringBuilder();
        int count = 1;

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;
            } else {
                compressed.append(str.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        // Append the last character and its count
        compressed.append(str.charAt(str.length() - 1)).append(count);

        return compressed.toString();
    }
}
