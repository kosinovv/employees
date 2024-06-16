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

    private String tabNum; //Табельный номер

    private String firstname; //Имя

    private String secondname; //Отчество

    private String lastname; //Фамилия

    private double salarySum; //Сумма оклада

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
