package com.example.projetointegrador.exceptions;

public class InvalidOrderTypeException extends RuntimeException {
    public InvalidOrderTypeException(String message) {
        super(message);
    }
}
