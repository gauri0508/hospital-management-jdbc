CREATE DATABASE hospitaldb;
USE hospitaldb;

CREATE TABLE Patient (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    dob DATE,
    gender ENUM('Male', 'Female', 'Other'),
    contact_info VARCHAR(150),
    primary_insurance_id BIGINT
);
