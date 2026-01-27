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

@RestController
@RequestMapping("/digi-fin-pocket/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Expense Service is running";
    }

    @PostMapping("/add")
    public ExpenseRequest addExpense(@RequestBody ExpenseRequest request) {
        return expenseService.addExpense(request);
    }

    @PutMapping("/update/{id}")
    public ExpenseRequest updateExpense(@PathVariable Long id, @RequestBody ExpenseRequest request) {
        return expenseService.updateExpense(id, request);
    }

    @GetMapping
    public List<ExpenseRequest> getExpenses() {

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        return expenseService.getExpenses(startDate, endDate);
    }

    @GetMapping("/{id}")
    public ExpenseRequest getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }
}
