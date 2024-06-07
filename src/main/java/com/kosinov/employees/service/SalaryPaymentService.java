package com.kosinov.employees.service;

import com.kosinov.employees.dto.EmployeeDTO;
import com.kosinov.employees.dto.SalaryPaymentDTO;
import com.kosinov.employees.dto.SalaryPaymentUpdateDTO;
import com.kosinov.employees.mapper.SalaryMapper;
import com.kosinov.employees.model.SalaryPayment;
import com.kosinov.employees.repository.SalariesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalaryPaymentService {

    //Сервис платежей
    private final SalariesRepository salariesRepository;
    private final SalaryMapper salaryMapper;

    //Сервис персонала
    private final EmployeeService employeeService;

    public SalaryPaymentDTO createSalary(SalaryPaymentDTO salaryPaymentDTO) {
        SalaryPayment salaryPayment = salaryMapper.toEntity(salaryPaymentDTO);
        if (employeeService.findEmployee(salaryPayment.getEmployeeId()) != null) {
            salariesRepository.add(salaryPayment);
        }
        return salaryMapper.toDto(salaryPayment);
    }

    public SalaryPaymentDTO findSalary(Integer id) {
        SalaryPayment findedSalary = salariesRepository.getById(id);
        return salaryMapper.toDto(findedSalary);
    }

    public String getEmpSalarySum(Integer employeeId) {
        EmployeeDTO employeeDTO = employeeService.findEmployee(employeeId);
        return String.format("%s %2s %3s за весь период получил платежей на сумму %4s",
                employeeDTO.getLastname(),
                employeeDTO.getFirstname(),
                employeeDTO.getSecondname(),
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
