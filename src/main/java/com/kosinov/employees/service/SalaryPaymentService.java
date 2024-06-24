package com.kosinov.employees.service;

import com.kosinov.employees.dto.SalaryPaymentDTO;
import com.kosinov.employees.dto.SalaryPaymentFullDTO;
import com.kosinov.employees.dto.SalaryPaymentUpdateDTO;
import com.kosinov.employees.exception.EmployeeNotFound;
import com.kosinov.employees.exception.SalaryPaymentNotFound;
import com.kosinov.employees.mapper.SalaryPaymentMapper;
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
    private final SalaryPaymentMapper salaryPaymentMapper;

    //Сервис персонала
    private final EmployeeService employeeService;

    public SalaryPaymentFullDTO createSalary(SalaryPaymentDTO salaryPaymentDTO) {
        Employee findedEmployee = employeeService.getEmployee(salaryPaymentDTO.getEmployeeTabNum());

        if (findedEmployee != null) {
            SalaryPayment salaryPayment = new SalaryPayment(
                    findedEmployee.getId(),
                    salaryPaymentDTO.getPaymentDate(),
                    salaryPaymentDTO.getSalarySum());

            salariesRepository.save(salaryPayment);
            return salaryPaymentMapper.toFullDto(salaryPayment);
        } else {
            throw new EmployeeNotFound(String.format("Сотрудник с табельным номером %s не найден", salaryPaymentDTO.getEmployeeTabNum()));
        }
    }

    public SalaryPaymentFullDTO findSalary(Integer id) {
        SalaryPayment findedSalary = salariesRepository.findById(id).orElseThrow(
            () -> new SalaryPaymentNotFound(String.format("Платеж с идентификатором %s не найден", id)));
        return salaryPaymentMapper.toFullDto(findedSalary);
    }

    public String getEmpSalarySum(String tabNum) {
        return salariesRepository.getSalarySumForEmp(tabNum);
    }

    public SalaryPaymentFullDTO deleteSalary(Integer id) {
        SalaryPayment salaryForDelete = salariesRepository.findById(id).orElseThrow(
                () -> new SalaryPaymentNotFound(String.format("Платеж с идентификатором %s не найден", id)));
        salariesRepository.deleteById(id);
        return salaryPaymentMapper.toFullDto(salaryForDelete);
    }

    public SalaryPaymentFullDTO updateSalary(SalaryPaymentUpdateDTO salaryPaymentUpdateDTO) {
        SalaryPayment salaryForUpdate = salariesRepository.findById(salaryPaymentUpdateDTO.getId()).orElseThrow(
                () -> new SalaryPaymentNotFound(String.format("Платеж с идентификатором %s не найден", salaryPaymentUpdateDTO.getId())));
        salaryPaymentMapper.update(salaryPaymentUpdateDTO,salaryForUpdate);
        salariesRepository.save(salaryForUpdate);
        return salaryPaymentMapper.toFullDto(salaryForUpdate);
    }

}
