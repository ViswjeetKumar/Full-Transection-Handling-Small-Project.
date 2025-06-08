-- Create database if not exists
CREATE DATABASE IF NOT EXISTS accounts;
USE accounts;

-- Create 'account' table
CREATE TABLE IF NOT EXISTS account (
    account_number INT PRIMARY KEY,
    holder_name VARCHAR(100) NOT NULL,
    balance DOUBLE NOT NULL CHECK (balance >= 0)
);

-- Insert sample data into 'account' table
INSERT INTO account (account_number, holder_name, balance) VALUES
(1001, 'Rahul Verma', 1200.00),
(1002, 'Sneha Mehta', 950.50),
(1003, 'Aman Kumar', 500.00),
(1004, 'Anjali Sharma', 3000.00),
(1005, 'Raj Patel', 50.00);
