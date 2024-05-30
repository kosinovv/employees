package com.kosinov.employees.model;

import lombok.Data;

@Data
public class Employee {

    private final String tabnum; //Табельный номер

    private final String firstname; //Имя

    private final String secondname; //Отчество

    private final String lastname; //Фамилия

    private final double salarysum; //Сумма оклада

    private final String department; //Отдел
}
