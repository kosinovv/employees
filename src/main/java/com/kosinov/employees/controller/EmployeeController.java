package com.kosinov.employees.controller;

import com.kosinov.employees.dto.EmployeeDTO;
import com.kosinov.employees.dto.EmployeeUpdateDTO;
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

    Employee findedEmployee;

    @PostMapping("create")
    public EmployeeDTO create(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeesMapper.toEntity(employeeDTO);
        employeesRepository.save(employee);
        EmployeeDTO returnDTO = employeesMapper.toDto(employee);
        return returnDTO;
    }

    @GetMapping("/find/{tabnum}")
    public Employee find(@PathVariable String tabnum) {
        findedEmployee = employeesRepository.getByTabNum(tabnum);
        return findedEmployee;
    }

    @DeleteMapping("delete/{tabnum}")
    public void delete(@PathVariable String tabnum) {
        if (findedEmployee != null) {
            employeesRepository.delete(findedEmployee);
            findedEmployee = null;
        }
    }

    @PutMapping("update")
    public EmployeeDTO update(@RequestBody EmployeeUpdateDTO employeeUpdateDTO ) {
        if (findedEmployee != null) {
            employeesMapper.update(employeeUpdateDTO,findedEmployee);
            employeesRepository.save(findedEmployee);
            EmployeeDTO returnDTO = employeesMapper.toDto(findedEmployee);
            return returnDTO;
        }
        return null;
    }
}
