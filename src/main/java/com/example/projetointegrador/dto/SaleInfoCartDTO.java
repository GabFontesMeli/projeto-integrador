package com.example.projetointegrador.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
public class SaleInfoCartDTO {
    private LocalDate date;
    private Double value;
    private Long userId;

    @Override
    public String toString() {
        return "SaleInfoCartDTO{" +
                "date=" + date +
                ", value=" + value +
                ", userId=" + userId +
                '}';
    }
}
