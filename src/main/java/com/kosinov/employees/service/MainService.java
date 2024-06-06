package com.kosinov.employees.service;

import com.kosinov.employees.dto.EmployeeDTO;
import com.kosinov.employees.dto.EmployeeUpdateDTO;
import com.kosinov.employees.dto.SalaryPaymentDTO;
import com.kosinov.employees.dto.SalaryPaymentUpdateDTO;
import com.kosinov.employees.mapper.EmployeeMapper;
import com.kosinov.employees.mapper.SalaryMapper;
import com.kosinov.employees.model.Employee;
import com.kosinov.employees.model.SalaryPayment;
import com.kosinov.employees.repository.EmployeesRepository;
import com.kosinov.employees.repository.SalariesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainService {

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

    //Раздел оплат

    private final SalariesRepository salariesRepository;
    private final SalaryMapper salaryMapper;

    public SalaryPaymentDTO createSalary(SalaryPaymentDTO salaryPaymentDTO) {
        SalaryPayment salaryPayment = salaryMapper.toEntity(salaryPaymentDTO);
        if (findEmployee(salaryPayment.getEmployeeId()) != null) {
            salariesRepository.add(salaryPayment);
        }
        return salaryMapper.toDto(salaryPayment);
    }

    public SalaryPaymentDTO findSalary(Integer id) {
        SalaryPayment findedSalary = salariesRepository.getById(id);
        return salaryMapper.toDto(findedSalary);
    }

    public String getEmpSalarySum(Integer employeeId) {
        Employee employee = employeesRepository.getById(employeeId);
        return String.format("%s %2s %3s за весь период получил платежей на сумму %4s",
                employee.getSecondname(),
                employee.getFirstname(),
                employee.getLastname(),
                salariesRepository.getEmpSalarySum(employeeId).toString());
    }

    public SalaryPaymentDTO deleteSalary(Integer id) {
        SalaryPayment salaryForDelete = salariesRepository.getById(id);
        salariesRepository.delete(salaryForDelete);
        return salaryMapper.toDto(salaryForDelete);
    }

    public SalaryPaymentDTO updateSalary(SalaryPaymentUpdateDTO salaryPaymentUpdateDTO) {
        SalaryPayment salaryForUpdate = salariesRepository.getById(salaryPaymentUpdateDTO.getId());
        salaryMapper.update(salaryPaymentUpdateDTO,salaryForUpdate);
        return salaryMapper.toDto(salaryForUpdate);
    }

}
