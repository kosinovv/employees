package com.kosinov.employees.repository;

import com.kosinov.employees.model.SalaryPayment;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class SalariesRepository {
    private final Set<SalaryPayment> salaryPayments = new HashSet<>();

    public SalaryPayment save(SalaryPayment salaryPayment) {
        salaryPayments.add(salaryPayment);
        return salaryPayment;
    }

    public double getSalariesByTabNum(String tabNum) {
        double salariessum = 0;

        return salariessum;
    }
}
