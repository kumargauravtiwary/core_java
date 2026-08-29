package java2;

import java.sql.*;

public class JdbcResourceManagement {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/testdb";

    private static final String USER = "postgres";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {

        String sql = """
                SELECT id, name, email
                FROM customers
                WHERE id = ?
                """;

        try (Connection connection =
                     DriverManager.getConnection(URL, USER, PASSWORD);

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, 101);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");

                    System.out.println(
                            id + " " + name + " " + email
                    );
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Database operation failed: "
                    + e.getMessage()
            );

            // Production:
            // logger.error("Database operation failed", e);
        }
    }
}
