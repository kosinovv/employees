package com.kosinov.employees.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SalaryPaymentUpdateDTO {

    private Integer id; //Идентификатор платежа

    private Date paymentDate; // Дата платежа

    private double salarySum; // Сумма платежа
}
