package com.kosinov.employees.service;

import com.kosinov.employees.dto.EmployeeDTO;
import com.kosinov.employees.dto.EmployeeUpdateDTO;
import com.kosinov.employees.dto.SalaryDTO;
import com.kosinov.employees.dto.SalaryUpdateDTO;
import com.kosinov.employees.mapper.EmployeeMapper;
import com.kosinov.employees.mapper.SalaryMapper;
import com.kosinov.employees.model.Employee;
import com.kosinov.employees.model.SalaryPayment;
import com.kosinov.employees.repository.EmployeesRepository;
import com.kosinov.employees.repository.SalariesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class MainService {

    //Раздел сотрудников

    private final EmployeesRepository employeesRepository;
    private final EmployeeMapper employeesMapper;

    Employee findedEmployee;

    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeesMapper.toEntity(employeeDTO);
        employeesRepository.add(employee);
        return employeesMapper.toDto(employee);
    }

    public Employee findEmployee(@PathVariable String tabNum) {
        findedEmployee = employeesRepository.getByTabNum(tabNum);
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
            return employeesMapper.toDto(findedEmployee);
        }
        return null;
    }

    //Раздел оплат

    private final SalariesRepository salariesRepository;
    private final SalaryMapper salaryMapper;

    SalaryPayment findedSalary;

    public SalaryDTO createSalary(@RequestBody SalaryDTO salaryDTO) {
        SalaryPayment salaryPayment = salaryMapper.toEntity(salaryDTO);
        if (findEmployee(salaryPayment.getEmployeeTabNum()) != null) {
            salariesRepository.add(salaryPayment);
            return salaryMapper.toDto(salaryPayment);
        }
        return null;
    }

    public SalaryPayment findSalary(@PathVariable String tabNum, @PathVariable Date dateSalary) {
        return salariesRepository.findSalary(tabNum, dateSalary);
    }

    public Double getEmpSalarySum(@PathVariable String tabNum, @PathVariable Date dateSalary) {
        return salariesRepository.getEmpSalarySum(tabNum, dateSalary);
    }

    public void deleteSalary() {
        if (findedSalary != null) {
            salariesRepository.delete(findedSalary);
            findedSalary = null;
        }
    }

    public SalaryDTO updateSalary(@RequestBody SalaryUpdateDTO salaryUpdateDTO) {
        if (findedSalary != null) {
            salaryMapper.update(salaryUpdateDTO,findedSalary);
            return salaryMapper.toDto(findedSalary);
        }
        return null;
    }

}
