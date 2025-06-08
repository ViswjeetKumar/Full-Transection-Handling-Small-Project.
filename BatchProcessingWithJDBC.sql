-- Create the 'students' database
CREATE DATABASE IF NOT EXISTS students;
USE students;

-- Create the 'student' table
CREATE TABLE IF NOT EXISTS student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL CHECK (age > 0),
    marks DOUBLE NOT NULL CHECK (marks >= 0)
);

-- Optional: Insert some default data
INSERT INTO student (name, age, marks) VALUES
('Amit Sharma', 21, 75.40),
('Pooja Verma', 22, 88.75),
('Nikhil Singh', 20, 60.20);
