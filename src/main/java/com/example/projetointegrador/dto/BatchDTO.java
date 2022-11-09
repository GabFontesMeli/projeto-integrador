package com.example.projetointegrador.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchDTO {
    private Long storageId;
    private Long sectionId;
    private Long productId;
    private LocalDate expirationDate;
    private LocalDate manufacturingDate;
    private LocalTime manufacturingTime;
    private Integer quantity;
    private Long providerBatchNumber;
}
