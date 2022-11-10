package com.example.projetointegrador.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.projetointegrador.model.BatchProduct;

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
    private LocalDate expirationDate;
    // private Long productId;
    // private LocalDate manufacturingDate;
    // private LocalTime manufacturingTime;
    // private Integer quantity;
    private List<BatchProduct> products;
}
