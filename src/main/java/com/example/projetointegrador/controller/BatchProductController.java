package com.example.projetointegrador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetointegrador.dto.ReportBatchProductDTO;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.service.interfaces.IBatchProductService;

@RestController
@RequestMapping("api/v1/fresh-products/due-date")
public class BatchProductController {
    
    @Autowired
    private IBatchProductService batchProductService;

    @GetMapping
    public ResponseEntity<ReportBatchProductDTO> getBatchProductExpiring(@RequestParam Long days, @RequestParam Long sectionId){
        return new ResponseEntity<>(batchProductService.getBatchProductExpiring(days, sectionId), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<ReportBatchProductDTO> getBatchProductExpiringOrdered(@RequestParam Long days, @RequestParam Long categoryId, @RequestParam String order){
        return new ResponseEntity<>(batchProductService.getBatchProductExpiringOrdered(days, categoryId, order), HttpStatus.OK);
    }

}
