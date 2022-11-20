package com.example.projetointegrador.controller;

import com.example.projetointegrador.dto.ProductInBatchDTO;
import com.example.projetointegrador.dto.ReportBatchProductDTO;
import com.example.projetointegrador.service.interfaces.IBatchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class BatchProductController {

    @Autowired
    private IBatchProductService batchProductService;

    @GetMapping("/due-date")
    public ResponseEntity<ReportBatchProductDTO> getBatchProductExpiring(@RequestParam Long days,
            @RequestParam Long sectionId) {
        return new ResponseEntity<>(batchProductService.getBatchProductExpiring(days, sectionId), HttpStatus.OK);
    }

    @GetMapping("/due-date/list")
    public ResponseEntity<ReportBatchProductDTO> getBatchProductExpiringOrdered(@RequestParam Long days,
            @RequestParam Long categoryId, @RequestParam String order) {
        return new ResponseEntity<>(batchProductService.getBatchProductExpiringOrdered(days, categoryId, order),
                HttpStatus.OK);
    }

    @GetMapping("/list/{productId}")
    public ResponseEntity<ProductInBatchDTO> findBatchProductsByProductId(@PathVariable Long productId) {
        return new ResponseEntity<>(batchProductService.findBatchProductsByProductId(productId), HttpStatus.OK);
    }

    @GetMapping("/list/{productId}/{order}")
    public ResponseEntity<ProductInBatchDTO> findBatchProductsByProductIdOrdered(@PathVariable Long productId,
            @PathVariable String order) {
        return new ResponseEntity<>(batchProductService.findBatchProductsByProductIdOrdered(productId, order), HttpStatus.OK);
    }
}
