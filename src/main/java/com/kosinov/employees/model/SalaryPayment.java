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

    private Integer employeeId; //Идентификатор сотрудника

    private Date paymentDate; // Дата платежа

    private double salarySum; // Сумма платежа

    public SalaryPayment(Integer employeeId, Date paymentDate, double salarySum) {
        this.employeeId = employeeId;
        this.paymentDate = paymentDate;
        this.salarySum = salarySum;
    }
}
