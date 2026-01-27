package com.expense.service.service;

import org.springframework.stereotype.Service;

import com.expense.service.dto.ExpenseRequest;
import com.expense.service.mapper.ExpenseMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExpenseService {

    private final ExpenseMapper expenseMapper;

    public ExpenseService(ExpenseMapper expenseMapper) {
        this.expenseMapper = expenseMapper;
    }

    public ExpenseRequest addExpense(ExpenseRequest expenseRequest) {
        log.info("Adding new expense: {}", expenseRequest);

        var expense = expenseMapper.toEntity(expenseRequest);
        log.debug("Converted to entity: {}", expense);

        var result = expenseMapper.toDto(expense);
        log.info("Expense added successfully");

        return result;
    }

}
