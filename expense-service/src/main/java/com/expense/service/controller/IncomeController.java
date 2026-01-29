package com.expense.service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.service.dto.IncomeRequest;
import com.expense.service.service.IncomeService;
import com.expense.service.util.IncomeSource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/digi-fin-pocket/income")
@Tag(name = "Income Controller", description = "Endpoints for managing income records")
public class IncomeController {

    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping
    @Operation(summary = "Add an income", description = "Create a new income record")
    public IncomeRequest addIncome(@RequestBody IncomeRequest incomeRequest) {
        log.info("Adding income: {}", incomeRequest);
        return incomeService.addIncome(incomeRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get income by ID", description = "Retrieve a specific income record by ID")
    public IncomeRequest getIncomeById(@PathVariable Long id) {
        log.info("Getting income by id: {}", id);
        return incomeService.getIncomeById(id);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get income by User ID", description = "Retrieve all income records for a specific user")
    public List<IncomeRequest> getIncomeByUserId(@PathVariable Long userId) {
        log.info("Getting income by userId: {}", userId);
        return incomeService.getIncomeByUserId(userId);
    }

    @GetMapping("/user/{userId}/date/{date}")
    @Operation(summary = "Get income by User ID and Date", description = "Retrieve income records for a specific user on a specific date")
    public List<IncomeRequest> getIncomeByUserIdAndDate(@PathVariable Long userId,
            @PathVariable java.time.LocalDate date) {
        log.info("Getting income by userId: {} and date: {}", userId, date);
        return incomeService.getIncomeByUserIdAndDate(userId, date);
    }

    @GetMapping("/user/{userId}/date/{startDate}/{endDate}")
    @Operation(summary = "Get income by User ID and Date Range", description = "Retrieve income records for a specific user within a date range")
    public List<IncomeRequest> getIncomeByUserIdAndDateRange(@PathVariable Long userId,
            @PathVariable java.time.LocalDate startDate,
            @PathVariable java.time.LocalDate endDate) {
        log.info("Getting income by userId: {} and date range: {} - {}", userId, startDate, endDate);
        return incomeService.getIncomeByUserIdAndDateRange(userId, startDate, endDate);
    }

    @GetMapping("/user/{userId}/source/{source}")
    @Operation(summary = "Get income by User ID and Source", description = "Retrieve income records for a specific user and source")
    public List<IncomeRequest> getIncomeByUserIdAndSource(@PathVariable Long userId,
            @PathVariable IncomeSource source) {
        log.info("Getting income by userId: {} and source: {}", userId, source);
        return incomeService.getIncomeByUserIdAndSource(userId, source);
    }

    @GetMapping("/user/{userId}/source/{source}/date/{date}")
    @Operation(summary = "Get income by User ID, Source and Date", description = "Retrieve income records for a specific user, source and date")
    public List<IncomeRequest> getIncomeByUserIdAndSourceAndDate(@PathVariable Long userId,
            @PathVariable IncomeSource source,
            @PathVariable java.time.LocalDate date) {
        log.info("Getting income by userId: {}, source: {} and date: {}", userId, source, date);
        return incomeService.getIncomeByUserIdAndSourceAndDate(userId, source, date);
    }

    @GetMapping("/user/{userId}/source/{source}/date/{startDate}/{endDate}")
    @Operation(summary = "Get income by User ID, Source and Date Range", description = "Retrieve income records for a specific user, source and date range")
    public List<IncomeRequest> getIncomeByUserIdAndSourceAndDateRange(@PathVariable Long userId,
            @PathVariable IncomeSource source,
            @PathVariable java.time.LocalDate startDate, @PathVariable java.time.LocalDate endDate) {
        log.info("Getting income by userId: {}, source: {} and date range: {} - {}", userId, source, startDate,
                endDate);
        return incomeService.getIncomeByUserIdAndSourceAndDateRange(userId, source, startDate, endDate);
    }

}
