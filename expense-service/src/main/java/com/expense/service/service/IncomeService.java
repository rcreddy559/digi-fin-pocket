package com.expense.service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.expense.service.dto.IncomeRequest;
import com.expense.service.mapper.IncomeMapper;
import com.expense.service.exception.IncomeNotFoundException;
import com.expense.service.repositry.IncomeRepositry;
import com.expense.service.util.IncomeSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IncomeService {

    private final IncomeRepositry incomeRepositry;
    private final IncomeMapper incomeMapper;

    public IncomeService(IncomeRepositry incomeRepositry, IncomeMapper incomeMapper) {
        this.incomeRepositry = incomeRepositry;
        this.incomeMapper = incomeMapper;
    }

    public IncomeRequest addIncome(IncomeRequest incomeRequest) {
        var income = incomeRepositry.save(incomeMapper.toEntity(incomeRequest));
        return incomeMapper.toDto(income);
    }

    public IncomeRequest getIncomeById(Long id) {
        log.info("Fetching income by id: {}", id);
        return incomeMapper
                .toDto(incomeRepositry.findById(id).orElseThrow(() -> {
                    log.error("Income not found with id: {}", id);
                    return new IncomeNotFoundException("Income not found with id: " + id);
                }));
    }

    public List<IncomeRequest> getIncomeByUserId(Long userId) {
        log.info("Fetching income for user: {}", userId);
        var incomes = incomeRepositry.findByUserId(userId).join();
        if (incomes.isEmpty()) {
            log.error("No income found for user: {}", userId);
            throw new IncomeNotFoundException("No income found for user: " + userId);
        }
        return incomes.stream().map(incomeMapper::toDto).toList();
    }

    public List<IncomeRequest> getIncomeByUserIdAndDate(Long userId, java.time.LocalDate date) {
        log.info("Fetching income for user: {} and date: {}", userId, date);
        var incomes = incomeRepositry.findByUserIdAndDate(userId, date).join();
        if (incomes.isEmpty()) {
            log.error("No income found for user: {} and date: {}", userId, date);
            throw new IncomeNotFoundException("No income found for user: " + userId + " and date: " + date);
        }
        return incomes.stream().map(incomeMapper::toDto).toList();
    }

    public List<IncomeRequest> getIncomeByUserIdAndDateRange(Long userId, java.time.LocalDate startDate,
            java.time.LocalDate endDate) {
        log.info("Fetching income for user: {} between {} and {}", userId, startDate, endDate);
        var incomes = incomeRepositry.findByUserIdAndDateBetween(userId, startDate, endDate).join();
        if (incomes.isEmpty()) {
            log.error("No income found for user: {} between {} and {}", userId, startDate, endDate);
            throw new IncomeNotFoundException(
                    "No income found for user: " + userId + " between " + startDate + " and " + endDate);
        }
        return incomes.stream().map(incomeMapper::toDto)
                .toList();
    }

    public List<IncomeRequest> getIncomeByUserIdAndSource(Long userId, IncomeSource source) {
        log.info("Fetching income for user: {} with source: {}", userId, source);
        var incomes = incomeRepositry.findByUserIdAndSource(userId, source).join();
        if (incomes.isEmpty()) {
            log.error("No income found for user: {} with source: {}", userId, source);
            throw new IncomeNotFoundException("No income found for user: " + userId + " with source: " + source);
        }
        return incomes.stream().map(incomeMapper::toDto).toList();
    }

    public List<IncomeRequest> getIncomeByUserIdAndSourceAndDate(Long userId, IncomeSource source,
            java.time.LocalDate date) {
        log.info("Fetching income for user: {} with source: {} on date: {}", userId, source, date);
        var incomes = incomeRepositry.findByUserIdAndSourceAndDate(userId, source, date).join();
        if (incomes.isEmpty()) {
            log.error("No income found for user: {} with source: {} on date: {}", userId, source, date);
            throw new IncomeNotFoundException(
                    "No income found for user: " + userId + " with source: " + source + " on date: " + date);
        }
        return incomes.stream().map(incomeMapper::toDto)
                .toList();
    }

    public List<IncomeRequest> getIncomeByUserIdAndSourceAndDateRange(Long userId, IncomeSource source,
            java.time.LocalDate startDate,
            java.time.LocalDate endDate) {
        log.info("Fetching income for user: {} with source: {} between {} and {}", userId, source, startDate, endDate);
        var incomes = incomeRepositry.findByUserIdAndSourceAndDateBetween(userId, source, startDate, endDate).join();
        if (incomes.isEmpty()) {
            log.error("No income found for user: {} with source: {} between {} and {}", userId, source, startDate,
                    endDate);
            throw new IncomeNotFoundException("No income found for user: " + userId + " with source: " + source
                    + " between " + startDate + " and " + endDate);
        }
        return incomes.stream()
                .map(incomeMapper::toDto).toList();
    }

}
