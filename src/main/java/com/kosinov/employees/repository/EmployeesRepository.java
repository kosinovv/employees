package com.kosinov.employees.repository;

import com.kosinov.employees.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<Employee, Integer> {

    Employee findByTabNum(String tabNum);

}
