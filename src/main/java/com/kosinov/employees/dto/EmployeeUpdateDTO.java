package com.kosinov.employees.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeUpdateDTO {

    private Integer id; //Идентификатор сотрудника

    private String firstname; //Имя

    private String secondname; //Отчество

    private String lastname; //Фамилия

    private double salarysum; //Сумма оклада

    private String department; //Отдел

}
