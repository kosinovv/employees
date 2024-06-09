package com.kosinov.employees.mapper;

import com.kosinov.employees.model.Employee;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setTabnum(resultSet.getString("tabnum"));
        employee.setFirstname(resultSet.getString("firstname"));
        employee.setSecondname(resultSet.getString("secondname"));
        employee.setLastname(resultSet.getString("lastname"));
        employee.setSalarysum(resultSet.getDouble("salarysum"));
        employee.setDepartment(resultSet.getString("department"));
        return employee;
    }
}

