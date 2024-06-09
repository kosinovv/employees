package com.kosinov.employees.service;

import com.kosinov.employees.dto.EmployeeDTO;
import com.kosinov.employees.dto.EmployeeUpdateDTO;
import com.kosinov.employees.exception.EmployeeNotFound;
import com.kosinov.employees.exception.SalaryPaymentNotFound;
import com.kosinov.employees.mapper.EmployeeMapper;
import com.kosinov.employees.model.Employee;
import com.kosinov.employees.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    //Раздел сотрудников

    private final EmployeesRepository employeesRepository;
    private final EmployeeMapper employeesMapper;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeesRepository.getByTabNum(employeeDTO.getTabnum());
        employeesRepository.add(employee);
        return employeeDTO;
    }

    public Employee getEmployee(String tabnum) {
        Employee findedEmployee = employeesRepository.getByTabNum(tabnum);
        return findedEmployee;
    }

    public EmployeeDTO findEmployee(String tabnum) {
        Employee findedEmployee = employeesRepository.getByTabNum(tabnum);
        return employeesMapper.toDto(findedEmployee);
    }

    public EmployeeDTO deleteEmployee(String tabnum) {
        Employee employeeForDelete = employeesRepository.delete(tabnum);
        return employeesMapper.toDto(employeeForDelete);
    }

    public EmployeeDTO updateEmployee(EmployeeUpdateDTO employeeUpdateDTO ) {
        Employee employeeForUpdate = employeesRepository.getByTabNum(employeeUpdateDTO.getTabnum());
        employeesMapper.update(employeeUpdateDTO,employeeForUpdate);
        return employeesMapper.toDto(employeeForUpdate);
    }

}
