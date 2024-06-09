package com.kosinov.employees.repository;

import com.kosinov.employees.exception.EmployeeNotFound;
import com.kosinov.employees.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class EmployeesRepository {

    private final JdbcTemplate jdbcTemplate;

    private final BeanPropertyRowMapper<Employee> beanPropertyRowMapper = new BeanPropertyRowMapper<>(Employee.class);

    public Employee add(Employee employee) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String preparedStatementString = "INSERT INTO employee (tabnum,firstname,secondname,lastname,salarysum,department) VALUES(?, ?, ?, ?, ?, ?);";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(preparedStatementString, new String[]{"id"});
            ps.setString(1,employee.getTabnum());
            ps.setString(2,employee.getFirstname());
            ps.setString(3,employee.getSecondname());
            ps.setString(4,employee.getLastname());
            ps.setDouble(5,employee.getSalarysum());
            ps.setString(6,employee.getDepartment());
            return ps;
        }, keyHolder);
        employee.setId(keyHolder.getKey().intValue());

        return employee;
    }

    public Employee delete(String tabNum) {

        Employee employeeForDelete = getByTabNum(tabNum);
        String statementString = "DELETE FROM employee WHERE id = ?";
        jdbcTemplate.update(statementString, employeeForDelete.getId());

        return employeeForDelete;
    }

    public Employee getByTabNum(String tabnum) {
        String statementString = "SELECT * FROM employee WHERE tabnum = ?";
        Optional<Employee> optionalEmployee = jdbcTemplate.query(statementString, beanPropertyRowMapper, new Object[]{tabnum}).stream().findFirst();
        return optionalEmployee.orElseThrow(() -> new EmployeeNotFound(String.format("Сотрудник с табельным номером %s не найден", tabnum)));
    }

    public Employee update(Employee emploueeForUpdate) {

        String statementString = "UPDATE employee SET firstname = ?, secondname = ?, lastname = ?, salary = ?, department = ?  WHERE id = ?";
        jdbcTemplate.update(statementString,
                emploueeForUpdate.getFirstname(),
                emploueeForUpdate.getSecondname(),
                emploueeForUpdate.getLastname(),
                emploueeForUpdate.getSalarysum(),
                emploueeForUpdate.getDepartment(),
                emploueeForUpdate.getId());
        return emploueeForUpdate;
    }
}
