package com.kosinov.employees.mapper;

import com.kosinov.employees.dto.EmployeeDTO;
import com.kosinov.employees.dto.EmployeeUpdateDTO;
import com.kosinov.employees.model.Employee;
import org.mapstruct.*;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EmployeeMapper {
    Employee toEntity(EmployeeDTO employeeDTO);
    EmployeeDTO toDto(Employee employee);
    Employee update(EmployeeUpdateDTO employeeDTO, @MappingTarget Employee employee);
}