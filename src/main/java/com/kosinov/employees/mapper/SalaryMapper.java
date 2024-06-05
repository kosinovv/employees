package com.kosinov.employees.mapper;

import com.kosinov.employees.dto.SalaryDTO;
import com.kosinov.employees.dto.SalaryUpdateDTO;
import com.kosinov.employees.model.SalaryPayment;
import org.mapstruct.*;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SalaryMapper {
    SalaryPayment toEntity(SalaryDTO salaryDTO);
    SalaryDTO toDto(SalaryPayment salaryPayment);

    void update(SalaryUpdateDTO updateDTO, @MappingTarget SalaryPayment salaryPayment);
}
