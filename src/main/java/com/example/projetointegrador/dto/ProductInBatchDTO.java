package com.example.projetointegrador.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInBatchDTO {
    private Long productId;
    private SectionDTO section;
    private List<BatchProductDTO> batchProducts;
}
