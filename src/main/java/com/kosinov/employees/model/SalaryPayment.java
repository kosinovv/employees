package com.kosinov.employees.model;

import lombok.Data;

import java.util.Date;

@Data
public class SalaryPayment {

    private Date paymentdate;

    private String employeeTabNum;

    private double salary;
}
