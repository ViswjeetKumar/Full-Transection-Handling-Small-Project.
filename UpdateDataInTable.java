import java.sql.*;

public class UpdateDataInTable {

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/students";
    private static final String USER = "root";
    private static final String PASSWORD = "EnterYourPass";

    public static void main(String[] args) {

        // Load JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
            return;
        }

        // SQL Update statement
        String query = "UPDATE student SET name = ?, age = ?, marks = ? WHERE id = ?";

        // Execute update
        try (
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pst = con.prepareStatement(query)
        ) {
            pst.setString(1, "Suraj Kumar");
            pst.setInt(2, 33);
            pst.setDouble(3, 49.36);
            pst.setInt(4, 5); // ID to update

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✓ Data updated successfully.");
            } else {
                System.out.println("✕ No rows updated. Check if the ID exists.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
