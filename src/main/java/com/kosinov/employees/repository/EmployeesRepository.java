package com.kosinov.employees.repository;

import com.kosinov.employees.exception.EmployeeNotFound;
import com.kosinov.employees.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class EmployeesRepository {
    private final Set<Employee> employees = new HashSet<>();

    public Employee add(Employee employee) {

        employees.add(employee);

        return employee;
    }

    public Employee delete(Employee employee) {

        employees.remove(employee);

        return employee;
    }

    public Employee getById(Integer id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFound(String.format("Сотрудник с идентификатором %s не найден",id)));
    }
}
