package com.example.projetointegrador.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchProductDTO {
    private Long batchId;
    private Integer remainingQuantity;
    private LocalDate expirationDate;
}
