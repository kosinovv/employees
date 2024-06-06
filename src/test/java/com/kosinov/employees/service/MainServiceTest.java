package com.kosinov.employees.service;

import com.kosinov.employees.dto.EmployeeDTO;
import com.kosinov.employees.mapper.EmployeeMapperImpl;
import com.kosinov.employees.mapper.SalaryMapperImpl;
import com.kosinov.employees.repository.EmployeesRepository;
import com.kosinov.employees.repository.SalariesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        MainService.class,
        EmployeeMapperImpl.class,
        SalaryMapperImpl.class})

class MainServiceTest {

    @Autowired
    private MainService mainService;

    @MockBean
    private EmployeesRepository employeesRepository;

    @MockBean
    private SalariesRepository salariesRepository;

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
        employeeDTO = mainService.createEmployee(employeeDTO);
    }

    @Test
    void findEmployee_test() {
        //Проверка работы метода добавления
        employeeDTO = mainService.findEmployee(employeeDTO.getId());
    }

}