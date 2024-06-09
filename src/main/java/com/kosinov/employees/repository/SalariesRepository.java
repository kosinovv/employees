package com.kosinov.employees.repository;

import com.kosinov.employees.exception.SalaryPaymentNotFound;
import com.kosinov.employees.model.SalaryPayment;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
@RequiredArgsConstructor
public class SalariesRepository {

    private final JdbcTemplate jdbcTemplate;

    private final BeanPropertyRowMapper<SalaryPayment> beanPropertyRowMapper = new BeanPropertyRowMapper<>(SalaryPayment.class);


    public SalaryPayment add(SalaryPayment salaryPayment) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String preparedStatementString = "INSERT INTO salary_payment (employeeid,paymentdate,salarysum) VALUES(?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(preparedStatementString, new String[]{"id"});
            ps.setInt(1,salaryPayment.getEmployeeId());
            ps.setDate(2,salaryPayment.getPaymentdate());
            ps.setDouble(3,salaryPayment.getSalarySum());
            return ps;
        }, keyHolder);
        salaryPayment.setId(keyHolder.getKey().intValue());
        return salaryPayment;
    }

    public SalaryPayment delete(Integer id) {

        SalaryPayment salaryForDelete = getById(id);
        String statementString = "DELETE FROM salary_payment WHERE id = ?";
        jdbcTemplate.update(statementString, id);

        return salaryForDelete;
    }

    public SalaryPayment getById(Integer id) {
        String statementString = "SELECT * FROM salary_payment WHERE id = ?";
        Optional<SalaryPayment> optionalSalaryPayment = jdbcTemplate.query(statementString, beanPropertyRowMapper, new Object[]{id}).stream().findFirst();
        return optionalSalaryPayment.orElseThrow(() -> new SalaryPaymentNotFound(String.format("Платеж с идентификатором %s не найден", id)));
    }

    public Double getEmpSalarySum(Integer employeeId) {
        String statementString = "SELECT sum(salarySum) FROM salary_payment WHERE employeeId = ?";
        return jdbcTemplate.queryForObject(statementString, Double.class, new Object[]{employeeId});
    }
}
