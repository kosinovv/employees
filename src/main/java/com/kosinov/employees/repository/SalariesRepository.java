package com.kosinov.employees.repository;

import com.kosinov.employees.model.SalaryPayment;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Repository
public class SalariesRepository {
    private final Set<SalaryPayment> salaryPayments = new HashSet<>();

    public void add(SalaryPayment salaryPayment) {
        salaryPayments.add(salaryPayment);
    }

    public void delete(SalaryPayment salaryPayment) {
        salaryPayments.remove(salaryPayment);
    }

    public SalaryPayment findSalary(String tabNum, Date dateSalary) {
        for (SalaryPayment salaryPayment : salaryPayments) {
            if (salaryPayment.getEmployeeTabNum().equals(tabNum)) {
                if (dateSalary != null) {
                    if (salaryPayment.getPaymentdate().equals(dateSalary)) {
                        return salaryPayment;
                    }
                } else {
                    return null;
                }
            }
        }

        return null;
    }

    public double getEmpSalarySum(String tabNum, Date dateSalary) {
        double salariessum = 0;

        for (SalaryPayment salaryPayment : salaryPayments) {
            if (salaryPayment.getEmployeeTabNum().equals(tabNum)) {
                if (dateSalary != null) {
                    if (salaryPayment.getPaymentdate().equals(dateSalary)) {
                      salariessum = salariessum + salaryPayment.getSalary();
                    }
                } else {
                    salariessum = salariessum + salaryPayment.getSalary();
                }
            }
        }

        return salariessum;
    }
}
