package com.example.projetointegrador.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CompletedFinanceReportCartDTO {
    private String financeReportByPeriod;
    private Double totalSalesValue;
    private List<SaleInfoCartDTO> salesInfo;
}
