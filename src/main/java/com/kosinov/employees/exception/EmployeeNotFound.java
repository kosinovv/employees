package com.kosinov.employees.exception;

public class EmployeeNotFound extends RuntimeException {

    public EmployeeNotFound(String message) {
        super(message);
    }
}
