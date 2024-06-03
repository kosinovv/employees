package com.kosinov.employees.service;

import com.kosinov.employees.mapper.EmployeeMapper;
import com.kosinov.employees.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeesRepository employeesRepository;

}
