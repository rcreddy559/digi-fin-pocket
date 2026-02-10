package com.user.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private LocalDateTime timestamp;
    private String error;
    private String message;
    private String path;

}
