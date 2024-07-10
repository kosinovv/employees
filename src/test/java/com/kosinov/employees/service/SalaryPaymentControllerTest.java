package com.kosinov.employees.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosinov.employees.controller.SalaryPaymentController;
import com.kosinov.employees.dto.SalaryPaymentDTO;
import com.kosinov.employees.dto.SalaryPaymentFullDTO;
import com.kosinov.employees.dto.SalaryPaymentUpdateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SalaryPaymentController.class)
public class SalaryPaymentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SalaryPaymentService salaryPaymentService;

    @MockBean
    private SalaryPaymentDTO salaryPaymentDTO;

    @MockBean
    private SalaryPaymentFullDTO salaryPaymentFullDTO;

    @MockBean
    private SalaryPaymentUpdateDTO salaryPaymentUpdateDTO;

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

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void createSalaryPayment_test() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/salary/create")
                        .content(asJsonString(salaryPaymentDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
