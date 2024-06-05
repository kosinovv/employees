package com.kosinov.employees.controller;

import com.kosinov.employees.dto.SalaryDTO;
import com.kosinov.employees.dto.SalaryUpdateDTO;
import com.kosinov.employees.model.SalaryPayment;
import com.kosinov.employees.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("salary")
@RequiredArgsConstructor

public class SalaryPaymentController {
    private final MainService salaryPaymentsService;

    @PostMapping("create")
    public SalaryDTO create(@RequestBody SalaryDTO salaryDTO) {
        return salaryPaymentsService.createSalary(salaryDTO);
    }

    @GetMapping("find")
    public SalaryPayment find(@RequestParam(value="tabnum") String tabnum, @RequestParam(value="date") Date datesalary) {
        return salaryPaymentsService.findSalary(tabnum, datesalary);
    }

    @GetMapping("getsum")
    public Double getempsalarysum(@RequestParam(value="tabnum") String tabnum, @RequestParam(value="date", required=false) Date datesalary) {
        return salaryPaymentsService.getEmpSalarySum(tabnum, datesalary);
    }

    @DeleteMapping("delete")
    public void delete() {
        salaryPaymentsService.deleteSalary();
    }

    @PutMapping("update")
    public SalaryDTO update(@RequestBody SalaryUpdateDTO salaryUpdateDTO ) {
        return salaryPaymentsService.updateSalary(salaryUpdateDTO);
    }
}