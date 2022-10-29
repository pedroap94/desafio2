package com.pedro.accountmanager.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DetailsException {
    private String title;
    private int status;
    private String message;
    private LocalDateTime timestamp;
}
