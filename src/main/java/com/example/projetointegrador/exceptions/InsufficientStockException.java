package com.example.projetointegrador.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class InsufficientStockException extends Exception{
    public InsufficientStockException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    private List<String> errors;
}
