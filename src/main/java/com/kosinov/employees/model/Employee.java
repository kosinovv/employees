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

    private String tabnum; //Табельный номер

    private String firstname; //Имя

    private String secondname; //Отчество

    private String lastname; //Фамилия

    private double salarysum; //Сумма оклада

    private String department; //Отдел

    public Employee(String tabnum, String firstname, String secondname, String lastname, double salarysum, String department) {
        this.tabnum = tabnum;
        this.firstname = firstname;
        this.secondname = secondname;
        this.lastname = lastname;
        this.salarysum = salarysum;
        this.department = department;
    }
}
