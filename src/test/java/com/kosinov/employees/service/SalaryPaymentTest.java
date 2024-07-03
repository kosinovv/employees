package com.kosinov.employees.service;

import com.kosinov.employees.dto.SalaryPaymentDTO;
import com.kosinov.employees.dto.SalaryPaymentFullDTO;
import com.kosinov.employees.dto.SalaryPaymentUpdateDTO;
import com.kosinov.employees.mapper.SalaryPaymentMapper;
import com.kosinov.employees.mapper.SalaryPaymentMapperImpl;
import com.kosinov.employees.model.Employee;
import com.kosinov.employees.model.SalaryPayment;
import com.kosinov.employees.repository.SalariesRepository;
import com.kosinov.employees.repository.SalaryCachedRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

import java.text.ParseException
        ;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        SalaryPaymentService.class,
        SalaryPaymentMapperImpl.class})

public class SalaryPaymentTest {

    @Autowired
    private SalaryPaymentService salaryPaymentService;

    @MockBean
    private SalariesRepository salariesRepository;

    @MockBean
    private SalaryCachedRepository salaryCachedRepository;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private SalaryPaymentDTO salaryPaymentDTO;

    @MockBean
    private SalaryPaymentFullDTO salaryPaymentFullDTO;

    @MockBean
    private SalaryPaymentUpdateDTO salaryPaymentUpdateDTO;

    @MockBean
    private SalaryPaymentMapper salaryPaymentMapper;

    @MockBean
    private Employee employee;

    @MockBean
    private SalaryPayment salaryPayment;

    @BeforeEach
    public void setupSalaryPayment() throws ParseException {
        salaryPaymentDTO = new SalaryPaymentDTO();
        salaryPaymentDTO.setEmployeeTabNum("1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date tmpDate = sdf.parse("2024-06-10");
        Date salaryDate = new Date(tmpDate.getTime());
        salaryPaymentDTO.setPaymentDate(salaryDate);
        salaryPaymentDTO.setSalarySum(2000.0);

        salaryPaymentFullDTO = new SalaryPaymentFullDTO();
        salaryPaymentFullDTO.setId(1);
        salaryPaymentFullDTO.setEmployeeId(1);
        salaryPaymentFullDTO.setPaymentDate(salaryDate);
        salaryPaymentFullDTO.setSalarySum(2000.0);

        salaryPaymentUpdateDTO = new SalaryPaymentUpdateDTO();
        salaryPaymentUpdateDTO.setId(1);
        salaryPaymentUpdateDTO.setPaymentDate(salaryDate);
        salaryPaymentUpdateDTO.setSalarySum(3000.0);
    }

    @Test
    void createSalaryPayment_test() {
        when(employeeService.getEmployee(salaryPaymentDTO.getEmployeeTabNum())).thenReturn(employee);

        //Проверка работы метода добавления
        SalaryPaymentFullDTO salaryPaymentTestDTO = salaryPaymentService.createSalary(salaryPaymentDTO);

        //Проверка результата
        verify(employeeService).getEmployee(any());
        verify(salariesRepository).save(any());
    }

    @Test
    void findSalaryPayment_test() {
        //Подготовка
        when(salaryCachedRepository.findById(salaryPaymentFullDTO.getId())).thenReturn(Optional.ofNullable(salaryPayment));

        //Проверка работы метода поиска
        salaryPaymentService.findSalary(salaryPaymentFullDTO.getId());

        //Проверка результата
        verify(salaryCachedRepository).findById(salaryPaymentFullDTO.getId());
    }

    @Test
    void updateSalaryPayment_test() {
        //Подготовка
        when(salaryCachedRepository.findById(salaryPaymentFullDTO.getId())).thenReturn(Optional.ofNullable(salaryPayment));

        //Проверка работы метода удаления
        salaryPaymentService.updateSalary(salaryPaymentUpdateDTO);

        //Проверка результата
        verify(salaryCachedRepository).findById(salaryPaymentUpdateDTO.getId());
        verify(salariesRepository).save(any());
    }

    @Test
    void deleleSalaryPayment_test() {
        //Подготовка
        when(salaryCachedRepository.findById(salaryPaymentFullDTO.getId())).thenReturn(Optional.ofNullable(salaryPayment));

        //Проверка работы метода удаления
        salaryPaymentService.deleteSalary(salaryPaymentFullDTO.getId());

        //Проверка результата
        verify(salaryCachedRepository).findById(salaryPaymentUpdateDTO.getId());
        verify(salariesRepository).deleteById(any());
    }
}
