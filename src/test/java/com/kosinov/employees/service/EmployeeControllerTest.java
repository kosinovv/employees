package com.kosinov.employees.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosinov.employees.controller.EmployeeController;
import com.kosinov.employees.dto.EmployeeDTO;
import com.kosinov.employees.dto.EmployeeUpdateDTO;
import com.kosinov.employees.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private EmployeeDTO employeeDTO;

    @MockBean
    private EmployeeUpdateDTO employeeUpdateDTO;

    @MockBean
    private Employee employee;

    @BeforeEach
    public void setupEmployee(){
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

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void createEmployee_test() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders
                        .post("/employee/create")
                        .content(asJsonString(employeeDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }
}
