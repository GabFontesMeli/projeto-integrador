package com.example.projetointegrador.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class ReportProductDTO {
    private Long batchId;
    private Long productId;
    private Long categoryId;
    private LocalDate expirationDate;
    private Integer quantity;
}
