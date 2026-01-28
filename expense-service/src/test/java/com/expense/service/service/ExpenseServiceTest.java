package com.expense.service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.expense.service.dto.ExpenseRequest;
import com.expense.service.entity.Expense;
import com.expense.service.exception.ExpenseNotFoundException;
import com.expense.service.mapper.ExpenseMapper;
import com.expense.service.repositry.ExpenseRepositry;
import com.expense.service.util.ExpenseCategory;
import com.expense.service.util.PaymentMode;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Mock
    private ExpenseMapper expenseMapper;

    @Mock
    private ExpenseRepositry repositry;

    @InjectMocks
    private ExpenseService expenseService;

    private Expense expense;
    private ExpenseRequest expenseRequest;

    @BeforeEach
    void setUp() {
        expense = Expense.builder()
                .id(1L)
                .userId(1L)
                .amount(BigDecimal.valueOf(100.0))
                .category(ExpenseCategory.FOOD)
                .expenseDate(LocalDate.now())
                .paymentMode(PaymentMode.CASH)
                .description("Lunch")
                .build();

        expenseRequest = ExpenseRequest.builder()
                .userId(1L)
                .amount(BigDecimal.valueOf(100.0))
                .category(ExpenseCategory.FOOD)
                .expenseDate(LocalDate.now())
                .paymentMode(PaymentMode.CASH)
                .description("Lunch")
                .build();
    }

    @Test
    void addExpense_Success() {
        when(expenseMapper.toEntity(any(ExpenseRequest.class))).thenReturn(expense);
        when(repositry.save(any(Expense.class))).thenReturn(expense);
        when(expenseMapper.toDto(any(Expense.class))).thenReturn(expenseRequest);

        ExpenseRequest result = expenseService.addExpense(expenseRequest);

        assertNotNull(result);
        assertEquals(expenseRequest.getAmount(), result.getAmount());
        verify(repositry, times(1)).save(any(Expense.class));
    }

    @Test
    void getExpenseById_Success() {
        when(repositry.findById(1L)).thenReturn(Optional.of(expense));
        when(expenseMapper.toDto(any(Expense.class))).thenReturn(expenseRequest);

        ExpenseRequest result = expenseService.getExpenseById(1L);

        assertNotNull(result);
        assertEquals(expenseRequest.getAmount(), result.getAmount());
    }

    @Test
    void getExpenseById_NotFound() {
        when(repositry.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ExpenseNotFoundException.class, () -> expenseService.getExpenseById(1L));
    }

    @Test
    void updateExpense_Success() {
        when(repositry.findById(1L)).thenReturn(Optional.of(expense));
        when(repositry.save(any(Expense.class))).thenReturn(expense);
        when(expenseMapper.toDto(any(Expense.class))).thenReturn(expenseRequest);

        ExpenseRequest result = expenseService.updateExpense(1L, expenseRequest);

        assertNotNull(result);
        verify(repositry, times(1)).save(any(Expense.class));
    }

    @Test
    void deleteExpense_Success() {
        expenseService.deleteExpense(1L);
        verify(repositry, times(1)).deleteById(1L);
    }

    @Test
    void getExpenses_Success() {
        LocalDate now = LocalDate.now();
        when(repositry.findByExpenseDateBetween(now, now)).thenReturn(List.of(expense));
        when(expenseMapper.toDto(any(Expense.class))).thenReturn(expenseRequest);

        List<ExpenseRequest> results = expenseService.getExpenses(now, now);

        assertNotNull(results);
        assertEquals(1, results.size());
    }
}
