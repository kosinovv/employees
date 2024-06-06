package com.kosinov.employees.repository;

import com.kosinov.employees.exception.SalaryPaymentNotFound;
import com.kosinov.employees.model.SalaryPayment;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class SalariesRepository {
    private final Set<SalaryPayment> salaryPayments = new HashSet<>();

    public SalaryPayment add(SalaryPayment salaryPayment) {

        salaryPayments.add(salaryPayment);

        return salaryPayment;
    }

    public SalaryPayment delete(SalaryPayment salaryPayment) {

        salaryPayments.remove(salaryPayment);

        return salaryPayment;
    }

    public SalaryPayment getById(Integer id) {
        return salaryPayments.stream()
                .filter(salaryPayment -> salaryPayment.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new SalaryPaymentNotFound(String.format("Платеж с идентификатором %s не найден", id)));
    }

    public Double getEmpSalarySum(Integer employeeId) {
        double salariessum = 0;

        for (SalaryPayment salaryPayment : salaryPayments) {
            if (salaryPayment.getEmployeeId().equals(employeeId)) {
               salariessum = salariessum + salaryPayment.getSalarySum();
            }
        }

        return salariessum;
    }
}
