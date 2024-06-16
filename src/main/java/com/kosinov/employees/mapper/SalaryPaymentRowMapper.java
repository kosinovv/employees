package com.kosinov.employees.mapper;

import com.kosinov.employees.model.SalaryPayment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SalaryPaymentRowMapper implements RowMapper<SalaryPayment> {
    @Override
    public SalaryPayment mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        SalaryPayment salaryPayment = new SalaryPayment();
        salaryPayment.setId(resultSet.getInt("id"));
        salaryPayment.setEmployeeId(resultSet.getInt("employeeId"));
        salaryPayment.setPaymentDate(resultSet.getDate("paymentDate"));
        salaryPayment.setSalarySum(resultSet.getDouble("salarySum"));
        return salaryPayment;
    }
}