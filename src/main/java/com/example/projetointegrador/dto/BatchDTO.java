package com.example.projetointegrador.dto;

import com.example.projetointegrador.model.BatchProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BatchDTO {
    private Long storageId;
    private Long sectionId;
    private LocalDate expirationDate;
    private Set<BatchProduct> products;
}
