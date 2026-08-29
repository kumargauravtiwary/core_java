package java2;

import java.io.*;
import java.sql.*;

public class PreciseRethrowDemo {

    public static void main(String[] args) {

        try {
            process();
        } catch (IOException | SQLException e) {

            System.out.println(
                    "Operation failed: " + e.getMessage()
            );
        }
    }

    public static void process()
            throws IOException, SQLException {

        try {

            readFile();
            connectDatabase();

        } catch (Exception e) {

            // Java 7 compiler knows that e can only be
            // IOException or SQLException here.
            throw e;
        }
    }

    private static void readFile()
            throws IOException {

        try (FileInputStream file =
                     new FileInputStream("data.txt")) {

            file.read();
        }
    }

    private static void connectDatabase()
            throws SQLException {

        throw new SQLException(
                "Database unavailable"
        );
    }
}
