package com.kosinov.employees.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SalaryPaymentDTO {

    private String employeetabnum; //Табельный номер сотрудника

    private Date paymentdate; // Дата платежа

    private double salarysum; // Сумма платежа
}
