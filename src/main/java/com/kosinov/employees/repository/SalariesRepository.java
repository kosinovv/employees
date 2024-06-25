package com.kosinov.employees.repository;

import com.kosinov.employees.model.SalaryPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SalariesRepository extends JpaRepository<SalaryPayment, Integer> {
    @Query(
            value = "select e.lastname||' '||e.firstname||' '||e.secondname||" +
                    "' за весь период получил платежей на сумму '||SUM(s.salarySum) " +
                    "from SalaryPayment s " +
                    "join Employee e on e.id = s.employeeId and e.tabNum = :tabNum " +
                    "group by e.lastname, e.firstname, e.secondname"
    )
    String getSalarySumForEmp(String tabNum);

}
