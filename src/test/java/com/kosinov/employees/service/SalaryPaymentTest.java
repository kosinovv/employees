package com.kosinov.employees.service;

import com.kosinov.employees.dto.SalaryPaymentDTO;
import com.kosinov.employees.mapper.SalaryMapperImpl;
import com.kosinov.employees.model.SalaryPayment;
import com.kosinov.employees.repository.SalariesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        SalaryPaymentService.class,
        SalaryMapperImpl.class})

public class SalaryPaymentTest {

    @Autowired
    private SalaryPaymentService salaryPaymentService;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private SalariesRepository salariesRepository;

    @MockBean
    private SalaryPaymentDTO salaryPaymentDTO;

    @MockBean
    private SalaryPayment salaryPayment;

    @BeforeEach
    public void setupSalaryPayment() throws ParseException {
        salaryPaymentDTO = new SalaryPaymentDTO();
        salaryPaymentDTO.setEmployeeTabNum("1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date salaryDate = sdf.parse("2024-06-10");
        salaryPaymentDTO.setPaymentDate(salaryDate);
        salaryPaymentDTO.setSalarySum(2000.0);
    }

    @Test
    void createSalaryPayment_test() {
        //Проверка работы метода добавления
        salaryPaymentDTO = salaryPaymentService.createSalary(salaryPaymentDTO);
    }

    @Test
    void findSalaryPayment_test() {
        //Проверка работы метода поиска
        //salaryPaymentDTO = salaryPaymentService.findSalary(1);
    }

    @Test
    void deleleSalaryPayment_test() {
        //Проверка работы метода поиска
        //salaryPaymentDTO = salaryPaymentService.deleteSalary(1);
    }
}
