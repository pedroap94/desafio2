package com.pedro.accountmanager.handler;

import com.pedro.accountmanager.exception.ContaException;
import com.pedro.accountmanager.exception.DetailsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DetailsException> argumentNotValid(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(DetailsException.builder()
                .title("Falha na requisição. Argumentos inválidos")
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DetailsException> jsonParseNotValid(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(DetailsException.builder()
                .title("Falha na requisição. Argumentos inválidos")
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContaException.class)
    public ResponseEntity<DetailsException> contaExceptionHandler(ContaException e) {
        return new ResponseEntity<>(DetailsException.builder()
                .title("Falha na requisição.")
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }

}
