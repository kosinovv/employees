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
    SalaryPayment toEntity(SalaryPaymentDTO salaryPaymentDTO);

    SalaryPaymentDTO toDto(SalaryPayment salaryPayment);

    @Mapping(target = "employeeId", ignore = true)
    void update(SalaryPaymentUpdateDTO updateDTO, @MappingTarget SalaryPayment salaryPayment);
}
