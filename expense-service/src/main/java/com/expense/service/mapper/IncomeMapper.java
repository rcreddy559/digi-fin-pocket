package com.expense.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.expense.service.dto.IncomeRequest;
import com.expense.service.entity.Income;

@Mapper(componentModel = "spring")
public interface IncomeMapper {
    // Add mapping methods here when you create DTOs
    // Example:
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "source", source = "source")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "paymentMode", source = "paymentMode")
    @Mapping(target = "description", source = "description")
    IncomeRequest toDto(Income income);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "source", source = "source")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "paymentMode", source = "paymentMode")
    @Mapping(target = "description", source = "description")
    Income toEntity(IncomeRequest request);
}
