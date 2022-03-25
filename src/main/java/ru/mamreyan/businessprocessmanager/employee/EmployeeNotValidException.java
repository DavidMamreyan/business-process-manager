package ru.mamreyan.businessprocessmanager.employee;

class EmployeeNotValidException extends RuntimeException {
    public EmployeeNotValidException() {
        super("Employee is not valid");
    }
}