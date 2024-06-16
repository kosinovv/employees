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

    @Column(name = "employee_id", nullable = false)
    private Integer employeeId; //Идентификатор сотрудника

    @Column(name = "payment_date", nullable = false)
    private Date paymentDate; // Дата платежа

    @Column(name = "salary_sum", nullable = false)
    private double salarySum; // Сумма платежа

    public SalaryPayment(Integer employeeId, Date paymentDate, double salarySum) {
        this.employeeId = employeeId;
        this.paymentDate = paymentDate;
        this.salarySum = salarySum;
    }
}
