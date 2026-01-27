package com.expense.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.expense.service.dto.ExpenseRequest;
import com.expense.service.entity.Expense;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    // Add mapping methods here when you create DTOs
    // Example:
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "expenseDate", source = "expenseDate")
    @Mapping(target = "paymentMode", source = "paymentMode")
    @Mapping(target = "description", source = "description")
    ExpenseRequest toDto(Expense expense);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "expenseDate", source = "expenseDate")
    @Mapping(target = "paymentMode", source = "paymentMode")
    @Mapping(target = "description", source = "description")
    Expense toEntity(ExpenseRequest request);
}
