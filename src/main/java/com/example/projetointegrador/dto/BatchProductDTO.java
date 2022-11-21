package com.example.projetointegrador.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchProductDTO {
    private Long batchId;
    private Integer remainingQuantity;
    private LocalDate expirationDate;
}
