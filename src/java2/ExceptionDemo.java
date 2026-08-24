package java2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExceptionDemo {

    /*
     * CHECKED EXCEPTION
     *
     * FileNotFoundException extends Exception.
     * The compiler forces us to either:
     *   1. catch it, or
     *   2. declare it with throws.
     */
    public static String readFile(String fileName)
            throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(fileName));

        String content = scanner.nextLine();

        scanner.close();

        return content;
    }

    /*
     * UNCHECKED EXCEPTION
     *
     * IllegalArgumentException extends RuntimeException.
     *
     * The compiler does NOT force the caller to catch it.
     */
    public static void withdraw(double balance, double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Withdrawal amount must be positive"
            );
        }

        if (amount > balance) {
            throw new IllegalArgumentException(
                    "Insufficient funds"
            );
        }

        System.out.println(
                "Withdrawal successful: " + amount
        );
    }

    public static void main(String[] args) {

        // ==========================================
        // 1. CHECKED EXCEPTION
        // ==========================================

        try {

            String data = readFile("account.txt");

            System.out.println(data);

        } catch (FileNotFoundException e) {

            System.out.println(
                    "Checked Exception: File not found"
            );
        }


        // ==========================================
        // 2. UNCHECKED EXCEPTION
        // ==========================================

        try {

            withdraw(5000, 7000);

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Unchecked Exception: "
                    + e.getMessage()
            );
        }
    }
}
