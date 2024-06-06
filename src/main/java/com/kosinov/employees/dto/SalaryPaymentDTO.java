package com.kosinov.employees.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SalaryPaymentDTO {

    private Integer id; //Идентификатор платежа

    private Integer employeeId; //Идентификатор сотрудника

    private Date paymentdate; // Дата платежа

    private double salarySum; // Сумма платежа
}
