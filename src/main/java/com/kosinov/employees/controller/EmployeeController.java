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
    public EmployeeDTO find(@RequestParam(value="tabNum") String tabNum) {
        return employeeService.findEmployee(tabNum);
    }

    @DeleteMapping("delete")
    public EmployeeDTO delete(@RequestParam(value="tabNum") String tabNum) {
        return employeeService.deleteEmployee(tabNum);
    }

    @PutMapping("update")
    public EmployeeDTO update(@RequestBody EmployeeUpdateDTO employeeUpdateDTO ) {
        return employeeService.updateEmployee(employeeUpdateDTO);
    }
}
