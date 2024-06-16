package com.kosinov.employees.service;

import com.kosinov.employees.dto.SalaryPaymentDTO;
import com.kosinov.employees.dto.SalaryPaymentUpdateDTO;
import com.kosinov.employees.exception.SalaryPaymentNotFound;
import com.kosinov.employees.mapper.SalaryMapper;
import com.kosinov.employees.model.Employee;
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
        Employee findedEmployee = employeeService.getEmployee(salaryPaymentDTO.getEmployeetabnum());

        if (findedEmployee != null) {
            SalaryPayment salaryPayment = new SalaryPayment(
                    findedEmployee.getId(),
                    salaryPaymentDTO.getPaymentdate(),
                    salaryPaymentDTO.getSalarysum());

            salariesRepository.save(salaryPayment);
        }

        return salaryPaymentDTO;
    }

    public SalaryPaymentDTO findSalary(Integer id) {
        SalaryPayment findedSalary = salariesRepository.findById(id).orElseThrow(
            () -> new SalaryPaymentNotFound(String.format("Платеж с идентификатором %s не найден", id)));

        return salaryMapper.toDto(findedSalary);
    }

    public String getEmpSalarySum(String tabnum) {
        Employee findedEmployee = employeeService.getEmployee(tabnum);
        return String.format("%s %2s %3s за весь период получил платежей на сумму %4s",
                findedEmployee.getLastname(),
                findedEmployee.getFirstname(),
                findedEmployee.getSecondname(),
                salariesRepository.getSalarySumForEmp(findedEmployee.getId()).toString());
    }

    public SalaryPaymentDTO deleteSalary(Integer id) {
        SalaryPayment salaryForDelete = salariesRepository.findById(id).orElseThrow(
                () -> new SalaryPaymentNotFound(String.format("Платеж с идентификатором %s не найден", id)));
        salariesRepository.deleteById(id);
        return salaryMapper.toDto(salaryForDelete);
    }

    public SalaryPaymentDTO updateSalary(SalaryPaymentUpdateDTO salaryPaymentUpdateDTO) {
        SalaryPayment salaryForUpdate = salariesRepository.findById(salaryPaymentUpdateDTO.getId()).orElseThrow(
                () -> new SalaryPaymentNotFound(String.format("Платеж с идентификатором %s не найден", salaryPaymentUpdateDTO.getId())));
        salaryMapper.update(salaryPaymentUpdateDTO,salaryForUpdate);
        return salaryMapper.toDto(salaryForUpdate);
    }

}
