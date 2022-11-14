package com.example.projetointegrador.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class HandleExceptions {

    @ExceptionHandler(SectionInvalidException.class)
    public ResponseEntity<ExceptionDetails> handleInvalidFields(SectionInvalidException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("section invalid")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BatchInvalidException.class)
    public ResponseEntity<ExceptionDetails> handleInvalidFields(BatchInvalidException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("batch invalid")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserUNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleInvalidFields(UserUNotFoundException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("user not found")
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ExceptionDetails> handleInvalidFields(InsufficientStockException ex) {
        List<ExceptionDetails> exceptionDetailsList = new ArrayList<>();
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("insufficient stock")
                .message(ex.getErrors().toString())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
