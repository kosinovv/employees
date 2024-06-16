package com.kosinov.employees.repository;

import com.kosinov.employees.model.SalaryPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SalariesRepository extends JpaRepository<SalaryPayment, Integer> {
    @Query(
            value = "SELECT SUM(salarysum) FROM salary_payment WHERE employeeid = ?1",
            nativeQuery = true
    )
    Double getSalarySumForEmp(Integer employeeId);

}
