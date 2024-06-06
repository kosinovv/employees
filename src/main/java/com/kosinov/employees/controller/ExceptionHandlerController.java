package com.kosinov.employees.controller;

import com.kosinov.employees.dto.ErrorResponse;
import com.kosinov.employees.exception.EmployeeNotFound;
import com.kosinov.employees.exception.SalaryPaymentNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.kosinov.employees.model.InternalErrorStatus.EMPLOYEE_NOT_FOUND;
import static com.kosinov.employees.model.InternalErrorStatus.SALARY_PAYMENT_NOT_FOUND;
@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFound(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(EMPLOYEE_NOT_FOUND, e.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SalaryPaymentNotFound.class)
    public ResponseEntity<ErrorResponse> handleSalaryPaymentNotFound(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(SALARY_PAYMENT_NOT_FOUND, e.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
