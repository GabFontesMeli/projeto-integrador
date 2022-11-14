package com.example.projetointegrador.exceptions;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExceptionDetails {
    private String title;
    private int status;
    private String message;
    private LocalDateTime timeStamp;
}
