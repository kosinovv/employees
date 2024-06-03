package com.kosinov.employees.controller;

import com.kosinov.employees.dto.EmployeeDTO;
import com.kosinov.employees.dto.EmployeeUpdateDTO;
import com.kosinov.employees.model.Employee;
import com.kosinov.employees.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor

public class EmployeeController {
    private final MainService employeeService;

    @PostMapping("create")
    public EmployeeDTO create(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    @GetMapping("/find/{tabnum}")
    public Employee find(@PathVariable String tabnum) {
        return employeeService.findEmployee(tabnum);
    }

    @DeleteMapping("delete")
    public void delete() {
       employeeService.deleteEmployee();
    }

    @PutMapping("update")
    public EmployeeDTO update(@RequestBody EmployeeUpdateDTO employeeUpdateDTO ) {
        return employeeService.updateEmployee(employeeUpdateDTO);
    }
}
