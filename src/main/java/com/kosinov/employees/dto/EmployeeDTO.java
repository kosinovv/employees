package com.kosinov.employees.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {

//    private Integer id; //Идентификатор сотрудника

    private String tabNum; //Табельный номер

    private String firstname; //Имя

    private String secondname; //Отчество

    private String lastname; //Фамилия

    private double salarySum; //Сумма оклада

    private String department; //Отдел
}
