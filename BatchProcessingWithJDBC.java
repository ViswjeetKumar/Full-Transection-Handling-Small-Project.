import java.sql.*;
import java.util.Scanner;

public class BatchProcessingWithJDBC {

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/students";
    private static final String USER = "root";
    private static final String PASSWORD = "EnterYourPass";

    public static void main(String[] args) {

        // Load JDBC Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("✕ JDBC Driver not found: " + e.getMessage());
            return;
        }

        // SQL Query
        String query = "INSERT INTO student (name, age, marks) VALUES (?, ?, ?)";

        try (
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pst = con.prepareStatement(query);
            Scanner in = new Scanner(System.in)
        ) {
            while (true) {
                System.out.print("Enter Name   : ");
                String name = in.nextLine();

                int age;
                double marks;

                try {
                    System.out.print("Enter Age    : ");
                    age = Integer.parseInt(in.nextLine());

                    System.out.print("Enter Marks  : ");
                    marks = Double.parseDouble(in.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("‼ Invalid input. Please enter numeric values for age and marks.");
                    continue;
                }

                // Add to batch
                pst.setString(1, name);
                pst.setInt(2, age);
                pst.setDouble(3, marks);
                pst.addBatch();

                // Ask if user wants to continue
                System.out.print("Do you want to insert more records? [Y/N]: ");
                String choice = in.nextLine().trim();
                if (choice.equalsIgnoreCase("N")) {
                    break;
                }
                System.out.println("➡»⟫  Continue inserting...");
            }

            // Execute batch
            int[] results = pst.executeBatch();
            System.out.println("\n✓ Batch execution completed. Total records inserted: " + results.length);

        } catch (SQLException e) {
            System.out.println("✕ Database error: " + e.getMessage());
        }
    }
}
