package com.kosinov.employees.service;

import com.kosinov.employees.dto.EmployeeDTO;
import com.kosinov.employees.dto.EmployeeUpdateDTO;
import com.kosinov.employees.exception.EmployeeNotFound;
import com.kosinov.employees.mapper.EmployeeMapper;
import com.kosinov.employees.model.Employee;
import com.kosinov.employees.repository.EmployeeCachedRepository;
import com.kosinov.employees.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    //Раздел сотрудников

    private final EmployeesRepository employeesRepository;
    private final EmployeeCachedRepository employeeCachedRepository;
    private final EmployeeMapper employeesMapper;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(
                employeeDTO.getTabNum(),
                employeeDTO.getFirstname(),
                employeeDTO.getSecondname(),
                employeeDTO.getLastname(),
                employeeDTO.getSalarySum(),
                employeeDTO.getDepartment());
        employeesRepository.save(employee);
        return employeeDTO;
    }

    public Employee getEmployee(String tabNum) {
        return findInCacheOrDbByTabNum(tabNum);
    }

    public EmployeeDTO findEmployee(String tabNum) {
        Employee findedEmployee = findInCacheOrDbByTabNum(tabNum);
        return employeesMapper.toDto(findedEmployee);
    }

    public EmployeeDTO deleteEmployee(String tabNum) {
        Employee employeeForDelete = findInCacheOrDbByTabNum(tabNum);
        employeesRepository.deleteById(employeeForDelete.getId());
        return employeesMapper.toDto(employeeForDelete);
    }

    public EmployeeDTO updateEmployee(EmployeeUpdateDTO employeeUpdateDTO) {
        Employee employeeForUpdate = findInCacheOrDbByTabNum(employeeUpdateDTO.getTabNum());
        employeesMapper.update(employeeUpdateDTO, employeeForUpdate);
        employeesRepository.save(employeeForUpdate);
        return employeesMapper.toDto(employeeForUpdate);
    }

    private Employee findInCacheOrDbByTabNum(String tabNum) {
        Optional<Employee> employeeFromCache = employeeCachedRepository.findByTabNum(tabNum);

        if (employeeFromCache.isPresent()) {
            return employeeFromCache.get();
        }

        Employee employeeFromDb = employeesRepository.findByTabNum(tabNum);
        if (employeeFromDb == null) {
            throw new EmployeeNotFound(String.format("Сотрудник с табельным номером %s не найден", tabNum));
        }

        employeeCachedRepository.save(employeeFromDb);
        return employeeFromDb;
    }
}
