package com.example.projetointegrador.controller.report;

import com.example.projetointegrador.dto.report.SalesProductReportDTO;
import com.example.projetointegrador.exceptions.PeriodInvalidException;
import com.example.projetointegrador.service.report.SalesProductReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/sales-report")
public class SalesProductReportController {

    @Autowired
    private SalesProductReportService salesReportService;

    @GetMapping
    public ResponseEntity<SalesProductReportDTO> getSalesReport(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                                                @RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) throws Exception {
        return new ResponseEntity<>(salesReportService.getSalesProductReportByPeriod(start, end), HttpStatus.OK);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<SalesProductReportDTO> getSalesReportByUser(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                                       @RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                                                       @PathVariable Long idUser) throws PeriodInvalidException {
        return new ResponseEntity<>(salesReportService.getSalesProductReportByUserPeriod(start, end, idUser), HttpStatus.OK);
    }
}
