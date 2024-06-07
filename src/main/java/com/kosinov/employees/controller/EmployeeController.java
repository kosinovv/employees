package com.kosinov.employees.controller;

import com.kosinov.employees.dto.EmployeeDTO;
import com.kosinov.employees.dto.EmployeeUpdateDTO;
import com.kosinov.employees.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor

public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("create")
    public EmployeeDTO create(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    @GetMapping("find")
    public EmployeeDTO find(@RequestParam(value="id") Integer id) {
        return employeeService.findEmployee(id);
    }

    @DeleteMapping("delete")
    public EmployeeDTO delete(@RequestParam(value="id") Integer id) {
        return employeeService.deleteEmployee(id);
    }

    @PutMapping("update")
    public EmployeeDTO update(@RequestBody EmployeeUpdateDTO employeeUpdateDTO ) {
        return employeeService.updateEmployee(employeeUpdateDTO);
    }
}
