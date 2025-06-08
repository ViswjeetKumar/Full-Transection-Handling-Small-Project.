-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS students;
USE students;

-- Create the student table
CREATE TABLE IF NOT EXISTS student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL CHECK (age >= 0),
    marks DOUBLE NOT NULL CHECK (marks >= 0)
);

-- Insert initial sample data
INSERT INTO student (name, age, marks) VALUES
('Manohar Singh', 36, 83.45),
('Rahul Kumar', 22, 78.00),
('Anita Sharma', 20, 91.25),
('Ravi Verma', 25, 68.40),
('Sonia Mehta', 23, 88.10);
