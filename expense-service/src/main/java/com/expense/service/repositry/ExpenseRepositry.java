package com.expense.service.repositry;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.expense.service.dto.ExpenseRequest;
import com.expense.service.entity.Expense;

public interface ExpenseRepositry extends JpaRepository<Expense, Long> {

    @Query("SELECT e FROM Expense e WHERE e.expenseDate BETWEEN :startDate AND :endDate")
    List<Expense> findByExpenseDateBetween(@Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    List<ExpenseRequest> findByCategory(String category);
}