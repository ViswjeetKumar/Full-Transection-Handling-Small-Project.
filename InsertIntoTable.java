import java.sql.*;

public class InsertIntoTable {

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

        // SQL insert query
        String query = "INSERT INTO student (name, age, marks) VALUES (?, ?, ?)";

        // Insert data using PreparedStatement
        try (
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pst = con.prepareStatement(query)
        ) {
            pst.setString(1, "Manohar Singh");
            pst.setInt(2, 36);
            pst.setDouble(3, 83.45);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Data inserted successfully.");
            } else {
                System.out.println("❌ Data insertion failed.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
