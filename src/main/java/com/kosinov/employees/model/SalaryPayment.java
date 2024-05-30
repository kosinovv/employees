package com.kosinov.employees.model;

import lombok.Data;

import java.util.Date;

@Data
public class SalaryPayment {

    private final Employee employee;

    private final double salary;

    private final Date paymentdate;
}
