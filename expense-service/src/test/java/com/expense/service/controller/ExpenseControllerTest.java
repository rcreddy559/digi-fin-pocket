package com.expense.service.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.expense.service.dto.ExpenseRequest;
import com.expense.service.service.ExpenseService;
import com.expense.service.util.ExpenseCategory;
import com.expense.service.util.PaymentMode;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ExpenseController.class)
class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ExpenseService expenseService;

    @Autowired
    private ObjectMapper objectMapper;

    private ExpenseRequest expenseRequest;

    @BeforeEach
    void setUp() {
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
    void healthCheck_ShouldReturnSuccess() throws Exception {
        mockMvc.perform(get("/digi-fin-pocket/expense/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Expense Service is running"));
    }

    @Test
    void addExpense_ShouldReturnCreatedExpense() throws Exception {
        when(expenseService.addExpense(any(ExpenseRequest.class))).thenReturn(expenseRequest);

        mockMvc.perform(post("/digi-fin-pocket/expense/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expenseRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(100.0))
                .andExpect(jsonPath("$.category").value("FOOD"));
    }

    @Test
    void updateExpense_ShouldReturnUpdatedExpense() throws Exception {
        when(expenseService.updateExpense(eq(1L), any(ExpenseRequest.class))).thenReturn(expenseRequest);

        mockMvc.perform(put("/digi-fin-pocket/expense/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expenseRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(100.0));
    }

    @Test
    void getExpenseById_ShouldReturnExpense() throws Exception {
        when(expenseService.getExpenseById(1L)).thenReturn(expenseRequest);

        mockMvc.perform(get("/digi-fin-pocket/expense/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(100.0));
    }

    @Test
    void deleteExpense_ShouldReturnOk() throws Exception {
        mockMvc.perform(delete("/digi-fin-pocket/expense/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getTodayExpenses_ShouldReturnList() throws Exception {
        when(expenseService.getExpenses(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(List.of(expenseRequest));

        mockMvc.perform(get("/digi-fin-pocket/expense"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].amount").value(100.0));
    }
}
