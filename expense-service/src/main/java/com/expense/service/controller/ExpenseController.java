package com.expense.service.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.service.dto.ExpenseRequest;
import com.expense.service.service.ExpenseService;
import com.expense.service.util.ExpenseCategory;
import com.expense.service.util.PaymentMode;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import jakarta.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/digi-fin-pocket/expense")
@Tag(name = "Expense Controller", description = "Endpoints for managing expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Check if the expense service is running")
    public String healthCheck() {
        log.info("Health check");
        return "Expense Service is running";
    }

    @PostMapping("/add")
    @Operation(summary = "Add an expense", description = "Create a new expense entry")
    public ExpenseRequest addExpense(@Valid @RequestBody ExpenseRequest request) {
        log.info("Adding new expense: {}", request);
        return expenseService.addExpense(request);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update an expense", description = "Update an existing expense entry by ID")
    public ExpenseRequest updateExpense(@PathVariable Long id, @Valid @RequestBody ExpenseRequest request) {
        log.info("Updating expense by id: {}", id);
        return expenseService.updateExpense(id, request);
    }

    @GetMapping
    @Operation(summary = "Get today's expenses", description = "Retrieve all expenses recorded for today")
    public List<ExpenseRequest> getTodayExpenses() {
        log.info("Getting today's expenses");
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        return expenseService.getExpenses(startDate, endDate);
    }

    @GetMapping("/date-range/{startDate}/{endDate}")
    @Operation(summary = "Get expenses by date range", description = "Retrieve expenses within a specific date range")
    public List<ExpenseRequest> getExpensesByDateRange(@PathVariable LocalDate startDate,
            @PathVariable LocalDate endDate) {
        log.info("Getting expenses by date range: {} - {}", startDate, endDate);
        return expenseService.getExpenses(startDate, endDate);
    }

    @GetMapping("/category/{category}/date-range/{startDate}/{endDate}")
    @Operation(summary = "Get expenses by date range and category", description = "Retrieve expenses within a specific date range and category")
    public List<ExpenseRequest> getExpensesByDateRangeAndCategory(@PathVariable LocalDate startDate,
            @PathVariable LocalDate endDate, @PathVariable ExpenseCategory category) {
        log.info("Getting expenses by date range: {} - {}", startDate, endDate);
        return expenseService.getExpensesByCategory(startDate, endDate, category);
    }

    @GetMapping("/payment-mode/{paymentMode}/date-range/{startDate}/{endDate}")
    @Operation(summary = "Get expenses by date range and payment mode", description = "Retrieve expenses within a specific date range and payment mode")
    public List<ExpenseRequest> getExpensesByDateRangeAndPaymentMode(@PathVariable LocalDate startDate,
            @PathVariable LocalDate endDate, @PathVariable PaymentMode paymentMode) {
        log.info("Getting expenses by date range: {} - {}", startDate, endDate);
        return expenseService.getExpensesByPaymentMode(startDate, endDate, paymentMode);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get expense by ID", description = "Retrieve a specific expense entry by ID")
    public ExpenseRequest getExpenseById(@PathVariable Long id) {
        log.info("Getting expense by id: {}", id);
        return expenseService.getExpenseById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an expense", description = "Delete an existing expense entry by ID")
    public void deleteExpense(@PathVariable Long id) {
        log.info("Deleting expense by id: {}", id);
        expenseService.deleteExpense(id);
    }
}
