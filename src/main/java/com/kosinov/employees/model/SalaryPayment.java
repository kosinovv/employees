package com.kosinov.employees.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Table(name = "salary_payment")
@Entity
@NoArgsConstructor
public class SalaryPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sal_seq")
    @SequenceGenerator(name = "sal_seq", sequenceName = "salary_seq", allocationSize = 1)
    private Integer id; //Идентификатор платежа

    private Integer employeeid; //Идентификатор сотрудника

    private Date paymentdate; // Дата платежа

    private double salarysum; // Сумма платежа

    public SalaryPayment(Integer employeeid, Date paymentdate, double salarysum) {
        this.employeeid = employeeid;
        this.paymentdate = paymentdate;
        this.salarysum = salarysum;
    }
}
