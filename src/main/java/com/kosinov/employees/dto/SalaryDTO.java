package com.kosinov.employees.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SalaryDTO {

    private Date paymentdate;

    private String employeeTabNum;

    private double salary;
}
