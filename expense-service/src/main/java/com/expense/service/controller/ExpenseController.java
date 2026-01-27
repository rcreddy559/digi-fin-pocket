package com.expense.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
