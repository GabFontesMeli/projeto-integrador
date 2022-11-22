package com.example.projetointegrador.dto.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SalesProductReportListDTO {

    private String productName;
    private Integer quantity;

    public SalesProductReportListDTO(Object[] obj) {
        this.productName = (String) obj[0];
        this.quantity = obj[1] instanceof BigDecimal ? ((BigDecimal) obj[1]).intValue() : ((BigInteger) obj[1]).intValue();
    }
}
