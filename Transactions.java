import java.sql.*;
import java.util.Scanner;

public class Transactions {

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/accounts";
    private static final String USER = "root";
    private static final String PASSWORD = "vamforever2011";

    public static void main(String[] args) {

        // Load JDBC Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
            return;
        }

        // Establish database connection and handle transaction
        try (
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            Scanner in = new Scanner(System.in)
        ) {
            con.setAutoCommit(false);

            // Collect transaction details
            System.out.print("Enter Sender (Depositor) Account Number: ");
            int senderAccount = in.nextInt();

            System.out.print("Enter Receiver Account Number: ");
            int receiverAccount = in.nextInt();

            if (senderAccount == receiverAccount) {
                System.out.println("Sender and receiver accounts cannot be the same.");
                return;
            }

            System.out.print("Enter Amount to Transfer: ");
            double amount = in.nextDouble();

            if (amount <= 0) {
                System.out.println("Amount must be greater than 0.");
                return;
            }

            // Validate accounts
            if (!isAccountExists(con, senderAccount)) {
                System.out.println("Sender account does not exist.");
                return;
            }

            if (!isAccountExists(con, receiverAccount)) {
                System.out.println("Receiver account does not exist.");
                return;
            }

            // Check balance and execute transaction
            if (isSufficientBalance(con, senderAccount, amount)) {
                String withdrawQuery = "UPDATE account SET balance = balance - ? WHERE account_number = ?";
                String depositQuery = "UPDATE account SET balance = balance + ? WHERE account_number = ?";

                try (
                    PreparedStatement withdrawStmt = con.prepareStatement(withdrawQuery);
                    PreparedStatement depositStmt = con.prepareStatement(depositQuery)
                ) {
                    withdrawStmt.setDouble(1, amount);
                    withdrawStmt.setInt(2, senderAccount);

                    depositStmt.setDouble(1, amount);
                    depositStmt.setInt(2, receiverAccount);

                    withdrawStmt.executeUpdate();
                    depositStmt.executeUpdate();

                    con.commit();
                    System.out.println("Transaction Successful ✅");
                }
            } else {
                con.rollback();
                System.out.println("Transaction Failed ❌ - Insufficient Balance.");
            }

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    // Check if account exists
    private static boolean isAccountExists(Connection con, int accountNumber) {
        String query = "SELECT 1 FROM account WHERE account_number = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error checking account existence: " + e.getMessage());
            return false;
        }
    }

    // Check if sender has sufficient balance
    private static boolean isSufficientBalance(Connection con, int accountNumber, double amount) {
        String query = "SELECT balance FROM account WHERE account_number = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double currentBalance = rs.getDouble("balance");
                return amount <= currentBalance;
            } else {
                System.out.println("Account not found.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error checking balance: " + e.getMessage());
            return false;
        }
    }
}
