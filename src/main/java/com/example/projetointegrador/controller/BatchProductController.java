package com.example.projetointegrador.controller;

import com.example.projetointegrador.dto.ProductInBatchDTO;
import com.example.projetointegrador.service.interfaces.IBatchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fresh-products/list")
public class BatchProductController {

    @Autowired
    private IBatchProductService batchProductService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductInBatchDTO> findAllByProductId(@PathVariable Long productId) {
        return new ResponseEntity<>(batchProductService.findAllByProductId(productId), HttpStatus.OK);
    }
}
