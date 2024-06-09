package com.kosinov.employees.mapper;

import com.kosinov.employees.dto.EmployeeDTO;
import com.kosinov.employees.dto.EmployeeUpdateDTO;
import com.kosinov.employees.model.Employee;
import org.mapstruct.*;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    Employee toEntity(EmployeeDTO employeeDTO);


    EmployeeDTO toDto(Employee employee);

    @Mapping(target = "id", ignore = true)
    void update(EmployeeUpdateDTO updateDTO, @MappingTarget Employee employee);
}