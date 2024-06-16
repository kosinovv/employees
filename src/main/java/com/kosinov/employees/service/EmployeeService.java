package com.kosinov.employees.service;

import com.kosinov.employees.dto.EmployeeDTO;
import com.kosinov.employees.dto.EmployeeUpdateDTO;
import com.kosinov.employees.exception.EmployeeNotFound;
import com.kosinov.employees.mapper.EmployeeMapper;
import com.kosinov.employees.model.Employee;
import com.kosinov.employees.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    //Раздел сотрудников

    private final EmployeesRepository employeesRepository;
    private final EmployeeMapper employeesMapper;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(
                employeeDTO.getTabnum(),
                employeeDTO.getFirstname(),
                employeeDTO.getSecondname(),
                employeeDTO.getLastname(),
                employeeDTO.getSalarysum(),
                employeeDTO.getDepartment());
        employeesRepository.save(employee);
        return employeeDTO;
    }

    public Employee getEmployee(String tabnum) {
        return employeesRepository.findByTabnum(tabnum);
    }
  
    public EmployeeDTO findEmployee(String tabnum) {
        Employee findedEmployee = employeesRepository.findByTabnum(tabnum);
        if (findedEmployee == null) {
            throw new EmployeeNotFound(String.format("Сотрудник с табельным номером %s не найден", tabnum));
        }
        return employeesMapper.toDto(findedEmployee);
    }

    public EmployeeDTO deleteEmployee(String tabnum) {
        Employee employeeForDelete = employeesRepository.findByTabnum(tabnum);
        if (employeeForDelete == null) {
            throw new EmployeeNotFound(String.format("Сотрудник с табельным номером %s не найден", tabnum));
        }
        employeesRepository.deleteById(employeeForDelete.getId());
        return employeesMapper.toDto(employeeForDelete);
    }

    public EmployeeDTO updateEmployee(EmployeeUpdateDTO employeeUpdateDTO ) {
        Employee employeeForUpdate = employeesRepository.findByTabnum(employeeUpdateDTO.getTabnum());
        if (employeeForUpdate == null) {
            throw new EmployeeNotFound(String.format("Сотрудник с табельным номером %s не найден", employeeUpdateDTO.getTabnum()));
        }
        employeesMapper.update(employeeUpdateDTO,employeeForUpdate);
        employeesRepository.save(employeeForUpdate);
        return employeesMapper.toDto(employeeForUpdate);
    }

}
