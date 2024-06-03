package com.kosinov.employees.service;

import com.kosinov.employees.dto.EmployeeDTO;
import com.kosinov.employees.dto.EmployeeUpdateDTO;
import com.kosinov.employees.mapper.EmployeeMapper;
import com.kosinov.employees.model.Employee;
import com.kosinov.employees.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@RequiredArgsConstructor
public class MainService {

    private final EmployeesRepository employeesRepository;
    private final EmployeeMapper employeesMapper;

    Employee findedEmployee;

    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeesMapper.toEntity(employeeDTO);
        employeesRepository.save(employee);
        return employeesMapper.toDto(employee);
    }

    public Employee findEmployee(@PathVariable String tabnum) {
        findedEmployee = employeesRepository.getByTabNum(tabnum);
        return findedEmployee;
    }

    public void deleteEmployee() {
        if (findedEmployee != null) {
            employeesRepository.delete(findedEmployee);
            findedEmployee = null;
        }
    }

    public EmployeeDTO updateEmployee(@RequestBody EmployeeUpdateDTO employeeUpdateDTO ) {
        if (findedEmployee != null) {
            employeesMapper.update(employeeUpdateDTO,findedEmployee);
            employeesRepository.save(findedEmployee);
            return employeesMapper.toDto(findedEmployee);
        }
        return null;
    }
}
