package com.kosinov.employees.service;

import com.kosinov.employees.dto.EmployeeDTO;
import com.kosinov.employees.mapper.EmployeeMapperImpl;
import com.kosinov.employees.model.Employee;
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
        EmployeeMapperImpl.class})

class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeesRepository employeesRepository;

    @MockBean
    private EmployeeDTO employeeDTO;

    @MockBean
    private Employee employee;


    @BeforeEach
    public void setupEmployee(){
        employeeDTO = new EmployeeDTO();
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
        //Проверка работы метода поиска
        //employeeDTO = employeeService.findEmployee("1");
    }

    @Test
    void deleleEmployee_test() {
        //Проверка работы метода поиска
        //employeeDTO = employeeService.deleteEmployee("1");
    }

}