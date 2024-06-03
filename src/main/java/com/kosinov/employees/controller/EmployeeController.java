package com.kosinov.employees.controller;

import com.kosinov.employees.dto.EmployeeDTO;
import com.kosinov.employees.mapper.EmployeeMapper;
import com.kosinov.employees.model.Employee;
import com.kosinov.employees.repository.EmployeesRepository;
import com.kosinov.employees.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor

public class EmployeeController {
    private final EmployeesRepository employeesRepository;
    private final EmployeeMapper employeesMapper;

    @PostMapping("create")
    public EmployeeDTO create(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeesMapper.toEntity(employeeDTO);
        employeesRepository.save(employee);
        EmployeeDTO returnDTO = employeesMapper.toDto(employee);
        return returnDTO;
    }

    @GetMapping("/users/{tabnum}")
    public Employee redirect(@PathVariable String tabnum) {
        return employeesRepository.getByTabNum(tabnum);
    }
}
