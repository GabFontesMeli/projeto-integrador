package com.example.projetointegrador.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

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

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleInvalidFields(ProductNotFoundException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("product not found")
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryInvalidException.class)
    public ResponseEntity<ExceptionDetails> handleInvalidFields(CategoryInvalidException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("category invalid")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InsuficientVolumeException.class)
    public ResponseEntity<ExceptionDetails> handleInvalidFields(InsuficientVolumeException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("insuficient volume")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(StorageInvalidException.class)
    public ResponseEntity<ExceptionDetails> handleInvalidFields(StorageInvalidException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("storage invalid")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }


}
