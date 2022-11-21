package com.example.projetointegrador.controller;

import com.example.projetointegrador.exceptions.PeriodInvalidException;
import com.example.projetointegrador.service.SalesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/sales-report")
public class SalesReportController {

    @Autowired
    private SalesReportService salesReportService;

    @GetMapping
    public ResponseEntity<Object> getSalesReport(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                                 @RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) throws Exception {
        return new ResponseEntity<>(salesReportService.getSalesProductReportByPeriod(start, end), HttpStatus.OK);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<Object> getSalesReportByUser(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                                       @RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                                                       @PathVariable Long idUser) throws PeriodInvalidException {
        return new ResponseEntity<>(salesReportService.getSalesProductReportByUserPeriod(start, end, idUser), HttpStatus.OK);
    }
}
