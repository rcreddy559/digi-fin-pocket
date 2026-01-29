package com.expense.service.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                        HttpServletRequest request) {
                String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                                .collect(Collectors.joining(", "));
                log.error("Validation failed: {} at path: {}", errorMessage, request.getRequestURI());
                var errorResponse = new ErrorResponse(
                                HttpStatus.BAD_REQUEST.value(),
                                LocalDateTime.now(),
                                errorMessage,
                                "Validation error",
                                request.getRequestURI());
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(ExpenseNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleExpenseNotFoundException(ExpenseNotFoundException ex,
                        HttpServletRequest request) {
                log.error("Expense not found: {} at path: {}", ex.getMessage(), request.getRequestURI());
                var errorResponse = new ErrorResponse(
                                HttpStatus.NOT_FOUND.value(),
                                LocalDateTime.now(),
                                ex.getMessage(),
                                "Expense not found",
                                request.getRequestURI());
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(IncomeNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleIncomeNotFoundException(IncomeNotFoundException ex,
                        HttpServletRequest request) {
                log.error("Income not found: {} at path: {}", ex.getMessage(), request.getRequestURI());
                var errorResponse = new ErrorResponse(
                                HttpStatus.NOT_FOUND.value(),
                                LocalDateTime.now(),
                                ex.getMessage(),
                                "Income not found",
                                request.getRequestURI());
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {
                log.error("An unexpected error occurred: {} at path: {}", ex.getMessage(), request.getRequestURI(), ex);
                var errorResponse = new ErrorResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                LocalDateTime.now(),
                                ex.getMessage(),
                                "Internal server error",
                                request.getRequestURI());
                return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

}
