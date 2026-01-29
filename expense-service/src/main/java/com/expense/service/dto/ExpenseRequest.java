package com.expense.service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.expense.service.util.ExpenseCategory;
import com.expense.service.util.PaymentMode;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long userId;

    @NotNull
    @DecimalMin(value = "0.01", message = "Amount must be positive")
    private BigDecimal amount;

    @NotNull
    private ExpenseCategory category;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate expenseDate;

    @NotNull
    private PaymentMode paymentMode;

    private String description;
}
