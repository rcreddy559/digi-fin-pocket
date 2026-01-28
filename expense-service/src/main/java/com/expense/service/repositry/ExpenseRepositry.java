package com.expense.service.repositry;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.expense.service.entity.Expense;
import com.expense.service.util.ExpenseCategory;
import com.expense.service.util.PaymentMode;

public interface ExpenseRepositry extends JpaRepository<Expense, Long> {

        @Query("SELECT e FROM Expense e WHERE e.expenseDate BETWEEN :startDate AND :endDate")
        List<Expense> findByExpenseDateBetween(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

        @Query("SELECT e FROM Expense e WHERE e.expenseDate BETWEEN :startDate AND :endDate AND e.category = :category")
        List<Expense> findByExpenseDateBetweenAndCategory(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate, @Param("category") ExpenseCategory category);

        @Query("SELECT e FROM Expense e WHERE e.expenseDate BETWEEN :startDate AND :endDate AND e.paymentMode = :paymentMode")
        List<Expense> findByExpenseDateBetweenAndPaymentMode(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate, @Param("paymentMode") PaymentMode paymentMode);

        List<Expense> findByCategory(String category);
}