package com.kosinov.employees.dto;

import lombok.Data;

@Data
public class EmployeeUpdateDTO {

    private final String firstname; //Имя

    private final String secondname; //Отчество

    private final String lastname; //Фамилия

    private final double salarysum; //Сумма оклада

    private final String department; //Отдел
}
