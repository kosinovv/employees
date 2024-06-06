package com.kosinov.employees.model;

import lombok.Data;

@Data
public class Employee {

    private Integer id; //Идентификатор сотрудника

    private String tabnum; //Табельный номер

    private String firstname; //Имя

    private String secondname; //Отчество

    private String lastname; //Фамилия

    private double salarysum; //Сумма оклада

    private String department; //Отдел
}
