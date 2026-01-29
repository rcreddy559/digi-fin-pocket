package com.expense.service.repositry;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

import com.expense.service.entity.Income;
import com.expense.service.util.IncomeSource;

public interface IncomeRepositry extends JpaRepository<Income, Long> {

    @Async
    CompletableFuture<List<Income>> findByUserId(Long userId);

    @Async
    CompletableFuture<List<Income>> findByUserIdAndDate(Long userId, java.time.LocalDate date);

    @Async
    CompletableFuture<List<Income>> findByUserIdAndDateBetween(Long userId, java.time.LocalDate startDate,
            java.time.LocalDate endDate);

    @Async
    CompletableFuture<List<Income>> findByUserIdAndSource(Long userId, IncomeSource source);

    @Async
    CompletableFuture<List<Income>> findByUserIdAndSourceAndDate(Long userId, IncomeSource source,
            java.time.LocalDate date);

    @Async
    CompletableFuture<List<Income>> findByUserIdAndSourceAndDateBetween(Long userId, IncomeSource source,
            java.time.LocalDate startDate,
            java.time.LocalDate endDate);

}