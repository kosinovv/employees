package com.kosinov.employees.model;

import lombok.Data;

import java.sql.Date;

@Data
public class SalaryPayment {

    private Integer id; //Идентификатор платежа

    private Integer employeeId; //Идентификатор сотрудника

    private Date paymentdate; // Дата платежа

    private double salarySum; // Сумма платежа
}
