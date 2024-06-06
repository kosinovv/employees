package com.kosinov.employees.exception;

public class SalaryPaymentNotFound extends RuntimeException {

    public SalaryPaymentNotFound(String message) {
        super(message);
    }
}
