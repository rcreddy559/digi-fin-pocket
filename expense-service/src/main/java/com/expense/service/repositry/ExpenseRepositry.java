package com.expense.service.repositry;

import java.time.LocalDate;
import java.util.List;

import java.util.concurrent.CompletableFuture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import com.expense.service.entity.Expense;
import com.expense.service.util.ExpenseCategory;
import com.expense.service.util.PaymentMode;

public interface ExpenseRepositry extends JpaRepository<Expense, Long> {

        @Async
        @Query("SELECT e FROM Expense e WHERE e.expenseDate BETWEEN :startDate AND :endDate")
        CompletableFuture<List<Expense>> findByExpenseDateBetween(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

        @Async
        @Query("SELECT e FROM Expense e WHERE e.expenseDate BETWEEN :startDate AND :endDate AND e.category = :category")
        CompletableFuture<List<Expense>> findByExpenseDateBetweenAndCategory(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate, @Param("category") ExpenseCategory category);

        @Async
        @Query("SELECT e FROM Expense e WHERE e.expenseDate BETWEEN :startDate AND :endDate AND e.paymentMode = :paymentMode")
        CompletableFuture<List<Expense>> findByExpenseDateBetweenAndPaymentMode(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate, @Param("paymentMode") PaymentMode paymentMode);

        @Async
        CompletableFuture<List<Expense>> findByCategory(String category);
}
