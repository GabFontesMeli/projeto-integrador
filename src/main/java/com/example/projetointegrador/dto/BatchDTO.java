package com.example.projetointegrador.dto;

import com.example.projetointegrador.model.BatchProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BatchDTO {
    private Long storageId;
    private Set<BatchProduct> products;
}
