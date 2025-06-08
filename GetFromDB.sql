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
