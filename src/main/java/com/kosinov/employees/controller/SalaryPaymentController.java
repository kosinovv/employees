package com.kosinov.employees.controller;

import com.kosinov.employees.dto.SalaryPaymentDTO;
import com.kosinov.employees.dto.SalaryPaymentUpdateDTO;
import com.kosinov.employees.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("salary")
@RequiredArgsConstructor

public class SalaryPaymentController {

    private final MainService salaryPaymentsService;

    @PostMapping("create")
    public SalaryPaymentDTO create(@RequestBody SalaryPaymentDTO salaryPaymentDTO) {
        return salaryPaymentsService.createSalary(salaryPaymentDTO);
    }

    @GetMapping("find")
    public SalaryPaymentDTO find(@RequestParam(value="id") Integer id) {
        return salaryPaymentsService.findSalary(id);
    }

    @GetMapping("getempsalarysum")
    public String getempsalarysum(@RequestParam(value="employeeId") Integer employeeId) {
        return salaryPaymentsService.getEmpSalarySum(employeeId);
    }

    @DeleteMapping("delete")
    public SalaryPaymentDTO delete(@RequestParam(value="id") Integer id) {
        return salaryPaymentsService.deleteSalary(id);
    }

    @PutMapping("update")
    public SalaryPaymentDTO update(@RequestBody SalaryPaymentUpdateDTO salaryPaymentUpdateDTO) {
        return salaryPaymentsService.updateSalary(salaryPaymentUpdateDTO);
    }
}