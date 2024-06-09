package com.kosinov.employees.mapper;

import com.kosinov.employees.dto.SalaryPaymentDTO;
import com.kosinov.employees.dto.SalaryPaymentUpdateDTO;
import com.kosinov.employees.model.SalaryPayment;
import org.mapstruct.*;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface SalaryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employeeId", ignore = true)
    SalaryPayment toEntity(SalaryPaymentDTO salaryPaymentDTO);

    @Mapping(target = "employeeTabNum", ignore = true)
    SalaryPaymentDTO toDto(SalaryPayment salaryPayment);

    @Mapping(target = "employeeId", ignore = true)
    void update(SalaryPaymentUpdateDTO updateDTO, @MappingTarget SalaryPayment salaryPayment);
}
