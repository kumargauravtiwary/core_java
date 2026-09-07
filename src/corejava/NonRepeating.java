package corejava;

//Find the first non-repeating character in a string.
public class NonRepeating {
    
    public static void main(String[] args) {
        String input = "swiss";
        char result = firstNonRepeatingCharacter(input);
        if (result != '\0') {
            System.out.println("The first non-repeating character is: " + result);
        } else {
            System.out.println("No non-repeating character found.");
        }
    }
    // Method to find the first non-repeating character in a string
    public static char firstNonRepeatingCharacter(String str) {
        int[] charCount = new int[256]; // Assuming ASCII character set

        // Count the occurrences of each character
        for (char c : str.toCharArray()) {
            charCount[c]++;
        }

        // Find the first character with a count of 1
        for (char c : str.toCharArray()) {
            if (charCount[c] == 1) {
                return c;
            }
        }

        return '\0'; // Return null character if no non-repeating character is found
    }
}
