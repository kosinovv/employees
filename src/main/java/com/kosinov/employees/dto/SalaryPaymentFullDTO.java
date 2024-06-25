package com.kosinov.employees.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class SalaryPaymentFullDTO {

    private Integer id;

    private Integer employeeId;

    private Date paymentDate;

    private double salarySum;

}
