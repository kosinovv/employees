package com.kosinov.employees.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SalaryPaymentDTO {

    private String employeeTabNum; //Табельный номер сотрудника

    private Date paymentDate; // Дата платежа

    private double salarySum; // Сумма платежа
}
