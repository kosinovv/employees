package com.kosinov.employees.controller;

import com.kosinov.employees.dto.SalaryPaymentDTO;
import com.kosinov.employees.dto.SalaryPaymentFullDTO;
import com.kosinov.employees.dto.SalaryPaymentUpdateDTO;
import com.kosinov.employees.service.SalaryPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("salary")
@RequiredArgsConstructor

public class SalaryPaymentController {

    private final SalaryPaymentService salaryPaymentsService;

    @PostMapping("create")
    public SalaryPaymentFullDTO create(@RequestBody SalaryPaymentDTO salaryPaymentDTO) {
        return salaryPaymentsService.createSalary(salaryPaymentDTO);
    }

    @GetMapping("find")
    public SalaryPaymentFullDTO find(@RequestParam(value="id") Integer id) {
        return salaryPaymentsService.findSalary(id);
    }

    @GetMapping("getempsalarysum")
    public String getempsalarysum(@RequestParam(value="employeeTabNum") String employeeTabNum) {
        return salaryPaymentsService.getEmpSalarySum(employeeTabNum);
    }

    @DeleteMapping("delete")
    public SalaryPaymentFullDTO delete(@RequestParam(value="id") Integer id) {
        return salaryPaymentsService.deleteSalary(id);
    }

    @PutMapping("update")
    public SalaryPaymentFullDTO update(@RequestBody SalaryPaymentUpdateDTO salaryPaymentUpdateDTO) {
        return salaryPaymentsService.updateSalary(salaryPaymentUpdateDTO);
    }
}