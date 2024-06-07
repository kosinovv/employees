package com.kosinov.employees.service;

import com.kosinov.employees.dto.EmployeeDTO;
import com.kosinov.employees.dto.EmployeeUpdateDTO;
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
        Employee employee = employeesMapper.toEntity(employeeDTO);
        return employeesMapper.toDto(employeesRepository.add(employee));
    }

    public EmployeeDTO findEmployee(Integer id) {
        Employee findedEmployee = employeesRepository.getById(id);
        return employeesMapper.toDto(findedEmployee);
    }

    public EmployeeDTO deleteEmployee(Integer id) {
        Employee employeeForDelete = employeesRepository.getById(id);
        employeesRepository.delete(employeeForDelete);
        return employeesMapper.toDto(employeeForDelete);
    }

    public EmployeeDTO updateEmployee(EmployeeUpdateDTO employeeUpdateDTO ) {
        Employee employeeForUpdate = employeesRepository.getById(employeeUpdateDTO.getId());
        employeesMapper.update(employeeUpdateDTO,employeeForUpdate);
        return employeesMapper.toDto(employeeForUpdate);
    }

}
