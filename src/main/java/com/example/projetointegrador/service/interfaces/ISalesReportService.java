package com.example.projetointegrador.service.interfaces;

import com.example.projetointegrador.dto.SalesProductReportDTO;
import com.example.projetointegrador.dto.SalesProductReportListDTO;
import com.example.projetointegrador.exceptions.PeriodInvalidException;

import java.time.LocalDate;

public interface ISalesReportService {
    SalesProductReportDTO getSalesProductReportByPeriod(LocalDate start, LocalDate end) throws PeriodInvalidException;

    SalesProductReportDTO getSalesProductReportByUserPeriod(LocalDate start, LocalDate end, Long userId) throws PeriodInvalidException;

    SalesProductReportDTO getSalesProductReportByUsersPeriod(LocalDate start, LocalDate end);
}
