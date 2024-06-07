package com.kosinov.employees.service;

import com.kosinov.employees.dto.EmployeeDTO;
import com.kosinov.employees.mapper.EmployeeMapperImpl;
import com.kosinov.employees.mapper.SalaryMapperImpl;
import com.kosinov.employees.repository.EmployeesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        EmployeeService.class,
        EmployeeMapperImpl.class,
        SalaryMapperImpl.class})

class MainServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeesRepository employeesRepository;

    private EmployeeDTO employeeDTO;


    @BeforeEach
    public void setupEmployee(){
        employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1);
        employeeDTO.setTabnum("1");
        employeeDTO.setFirstname("Виктор");
        employeeDTO.setSecondname("Анатольевич");
        employeeDTO.setLastname("Косинов");
        employeeDTO.setDepartment("Support");
        employeeDTO.setSalarysum(2000);
    }    

    @Test
    void createEmployee_test() {
        //Проверка работы метода добавления
        employeeDTO = employeeService.createEmployee(employeeDTO);
    }

    @Test
    void findEmployee_test() {
        //Проверка работы метода добавления
        employeeDTO = employeeService.findEmployee(employeeDTO.getId());
    }

}