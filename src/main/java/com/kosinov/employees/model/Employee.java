package com.kosinov.employees.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "employee")
@Entity
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
    @SequenceGenerator(name = "emp_seq", sequenceName = "employee_seq", allocationSize = 1)
    private Integer id; //Идентификатор сотрудника

    @Column(name = "tab_num", nullable = false)
    private String tabNum; //Табельный номер

    @Column(name = "firstname", nullable = false)
    private String firstname; //Имя

    @Column(name = "secondname")
    private String secondname; //Отчество

    @Column(name = "lastname")
    private String lastname; //Фамилия

    @Column(name = "salary_sum", nullable = false)
    private double salarySum; //Сумма оклада

    @Column(name = "department", nullable = false)
    private String department; //Отдел

    public Employee(String tabNum, String firstname, String secondname, String lastname, double salarySum, String department) {
        this.tabNum = tabNum;
        this.firstname = firstname;
        this.secondname = secondname;
        this.lastname = lastname;
        this.salarySum = salarySum;
        this.department = department;
    }
}
