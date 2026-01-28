package com.expense.service.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.expense.service.dto.ExpenseRequest;
import com.expense.service.exception.ExpenseNotFoundException;
import com.expense.service.mapper.ExpenseMapper;
import com.expense.service.repositry.ExpenseRepositry;
import com.expense.service.util.ExpenseCategory;
import com.expense.service.util.PaymentMode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExpenseService {

    private final ExpenseMapper expenseMapper;
    private final ExpenseRepositry repositry;

    public ExpenseService(ExpenseMapper expenseMapper, ExpenseRepositry repositry) {
        this.expenseMapper = expenseMapper;
        this.repositry = repositry;
    }

    public ExpenseRequest addExpense(ExpenseRequest expenseRequest) {
        log.info("Adding new expense: {}", expenseRequest);

        var expense = expenseMapper.toEntity(expenseRequest);
        log.debug("Converted to entity: {}", expense);

        var result = expenseMapper.toDto(repositry.save(expense));
        log.info("Expense added successfully");

        return result;
    }

    public List<ExpenseRequest> getExpenses(LocalDate startDate, LocalDate endDate) {
        return repositry.findByExpenseDateBetween(startDate, endDate).stream().map(expenseMapper::toDto).toList();
    }

    public ExpenseRequest getExpenseById(Long id) {
        return expenseMapper.toDto(repositry.findById(id)
                .orElseThrow(() -> {
                    log.warn("Expense with id {} not found", id);
                    return new ExpenseNotFoundException("Expense not found with id: " + id);
                }));
    }

    public ExpenseRequest updateExpense(Long id, ExpenseRequest request) {
        var expense = repositry.findById(id)
                .orElseThrow(() -> {
                    log.warn("Failed to update: Expense with id {} not found", id);
                    return new ExpenseNotFoundException("Expense not found with id: " + id);
                });
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setExpenseDate(request.getExpenseDate());
        expense.setPaymentMode(request.getPaymentMode());
        expense.setDescription(request.getDescription());
        return expenseMapper.toDto(repositry.save(expense));
    }

    public void deleteExpense(Long id) {
        repositry.deleteById(id);
    }

    public List<ExpenseRequest> getExpensesByCategory(
            ExpenseCategory category,
            LocalDate startDate, LocalDate endDate) {
        return getExpensesByCategory(startDate, endDate, category);
    }

    public List<ExpenseRequest> getExpensesByCategory(LocalDate startDate,
            LocalDate endDate,
            ExpenseCategory category) {
        return repositry.findByExpenseDateBetweenAndCategory(startDate, endDate, category).stream()
                .map(expenseMapper::toDto).toList();
    }

    public List<ExpenseRequest> getExpensesByPaymentMode(LocalDate startDate,
            LocalDate endDate,
            PaymentMode paymentMode) {
        return repositry.findByExpenseDateBetweenAndPaymentMode(startDate, endDate, paymentMode).stream()
                .map(expenseMapper::toDto).toList();
    }

}
