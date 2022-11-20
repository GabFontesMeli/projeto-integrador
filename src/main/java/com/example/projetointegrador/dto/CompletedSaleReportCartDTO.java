package com.example.projetointegrador.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Builder
public class CompletedSaleReportCartDTO {
    private String salesReportByPeriod;
    private Double totalSalesValue;
    private List<SaleInfoCartDTO> salesInfo;
}
