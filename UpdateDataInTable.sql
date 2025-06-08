-- Create the 'students' database if it doesn't exist
CREATE DATABASE IF NOT EXISTS students;
USE students;

-- Create 'student' table
CREATE TABLE IF NOT EXISTS student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL CHECK (age > 0),
    marks DOUBLE NOT NULL CHECK (marks >= 0)
);

-- Insert sample records (including ID 5)
INSERT INTO student (name, age, marks) VALUES
('Amit Sharma', 21, 75.40),
('Pooja Verma', 22, 88.75),
('Nikhil Singh', 20, 60.20),
('Riya Das', 23, 91.15),
('Old Name', 30, 45.00); -- this is the record with ID = 5 (to be updated)
