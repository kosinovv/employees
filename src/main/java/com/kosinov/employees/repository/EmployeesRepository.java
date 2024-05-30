package com.kosinov.employees.repository;

import com.kosinov.employees.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class EmployeesRepository {
    private final Set<Employee> employees = new HashSet<>();

    public Employee save(Employee employee) {
        employees.add(employee);
        return employee;
    }

    public Employee getByTabNum(String tabNum) {
        return employees.stream()
                .filter(employee -> employee.getTabnum().equals(tabNum))
                .findFirst()
                .orElse(null);
    }
}