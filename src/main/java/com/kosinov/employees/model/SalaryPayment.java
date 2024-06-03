package com.kosinov.employees.model;

import lombok.Data;

import java.util.Date;

@Data
public class SalaryPayment {

    private Employee employee;

    private double salary;

    private Date paymentdate;
}
