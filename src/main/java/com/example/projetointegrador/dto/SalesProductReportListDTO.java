package com.example.projetointegrador.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SalesProductReportListDTO {

    private String productName;
    private Integer quantity;

    public SalesProductReportListDTO(Object[] obj) {
        this.productName = (String) obj[0];
        this.quantity = ((BigDecimal) obj[1]).intValue();
    }
}
