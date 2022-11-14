package com.example.projetointegrador.dto;

import java.time.LocalDate;
import java.util.Set;

import com.example.projetointegrador.model.BatchProduct;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BatchDTO {
    private Long storageId;
    private Long sectionId;
    private LocalDate expirationDate;
    private Set<BatchProduct> products;
}
