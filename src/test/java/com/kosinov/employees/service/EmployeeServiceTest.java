package com.kosinov.employees.service;

import com.kosinov.employees.dto.EmployeeDTO;
import com.kosinov.employees.dto.EmployeeUpdateDTO;
import com.kosinov.employees.mapper.EmployeeMapperImpl;
import com.kosinov.employees.model.Employee;
import com.kosinov.employees.repository.EmployeeCachedRepository;
import com.kosinov.employees.repository.EmployeesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


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
    private EmployeeCachedRepository employeeCachedRepository;

    @MockBean
    private EmployeeDTO employeeDTO;

    @MockBean
    private EmployeeUpdateDTO employeeUpdateDTO;

    @MockBean
    private Employee employee;


    @BeforeEach
    public void setupEmployee() {
        employeeDTO = new EmployeeDTO();
        employeeDTO.setTabNum("1");
        employeeDTO.setFirstname("Виктор");
        employeeDTO.setSecondname("Анатольевич");
        employeeDTO.setLastname("Косинов");
        employeeDTO.setDepartment("Support");
        employeeDTO.setSalarySum(2000);

        employeeUpdateDTO = new EmployeeUpdateDTO();
        employeeUpdateDTO.setTabNum("1");
        employeeUpdateDTO.setFirstname("Виктор");
        employeeUpdateDTO.setSecondname("Анатольевич");
        employeeUpdateDTO.setLastname("Косинов");
        employeeUpdateDTO.setDepartment("Support 1");
        employeeUpdateDTO.setSalarySum(3000);
    }

    @Test
    void createEmployee_test() {
        //Проверка работы метода добавления
        employeeDTO = employeeService.createEmployee(employeeDTO);

        //Проверка результата
        verify(employeesRepository).save(any());
    }

    @Test
    void findEmployee_test() {
        //Подготовка
        when(employeeCachedRepository.findByTabNum(employeeDTO.getTabNum())).thenReturn(Optional.ofNullable(employee));

        //Проверка работы метода поиска
        employeeDTO = employeeService.findEmployee(employeeDTO.getTabNum());

        //Проверка результата
        verify(employeeCachedRepository).findByTabNum(any());
        verify(employeeCachedRepository).findByTabNum(any());
    }

    @Test
    void deleleEmployee_test() {
        //Подготовка
        when(employeeCachedRepository.findByTabNum(employeeDTO.getTabNum())).thenReturn(Optional.ofNullable(employee));

        //Проверка работы метода поиска
        employeeDTO = employeeService.deleteEmployee(employeeDTO.getTabNum());

        //Проверка результата
        verify(employeeCachedRepository).findByTabNum(any());
        verify(employeesRepository).deleteById(any());
    }

    @Test
    void updateEmployee_test() {
        //Подготовка
        when(employeeCachedRepository.findByTabNum(employeeDTO.getTabNum())).thenReturn(Optional.ofNullable(employee));

        //Проверка работы метода удаления
        employeeService.updateEmployee(employeeUpdateDTO);

        //Проверка результата
        verify(employeeCachedRepository).findByTabNum(any());
        verify(employeesRepository).save(any());
    }

}