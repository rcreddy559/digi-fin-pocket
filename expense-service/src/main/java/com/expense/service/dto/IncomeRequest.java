package com.expense.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.expense.service.util.IncomeSource;
import com.expense.service.util.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomeRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long userId;
    private IncomeSource source;
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private PaymentMode paymentMode;
}
