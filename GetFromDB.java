import java.sql.*;

public class GetFromDB {

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/students";
    private static final String USER = "root";
    private static final String PASSWORD = "EnterYourPass";

    public static void main(String[] args) {

        // Load MySQL JDBC Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
            return;
        }

        // Connect and fetch data from database
        String query = "SELECT * FROM student";

        try (
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery()
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                double marks = rs.getDouble("marks");

                System.out.println("ID    : " + id);
                System.out.println("Name  : " + name);
                System.out.println("Age   : " + age);
                System.out.println("Marks : " + marks);
                System.out.println("--------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }
}




//-------------------------------------------------------------------------------------------------------------------

/*
For database and tables:-
-- schema.sql

-- Create database
CREATE DATABASE IF NOT EXISTS students;
USE students;

-- Create table
CREATE TABLE IF NOT EXISTS student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT CHECK (age >= 0),
    marks DOUBLE CHECK (marks >= 0)
);

-- Insert sample data
INSERT INTO student (name, age, marks) VALUES
('Rahul Sharma', 21, 85.75),
('Sneha Gupta', 23, 90.25),
('Aman Verma', 22, 78.50),
('Manohar Singh', 36, 83.45);


*/
