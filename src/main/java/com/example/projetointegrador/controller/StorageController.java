package com.example.projetointegrador.controller;

import com.example.projetointegrador.dto.ProductDTO;
import com.example.projetointegrador.exceptions.ProductNotFoundException;
import com.example.projetointegrador.service.interfaces.IBatchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fresh-products/storage")
public class StorageController {

    @Autowired
    private IBatchProductService batchProductService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getBatchProductsByProductIdAndStorage(@PathVariable Long productId) throws ProductNotFoundException {
        return new ResponseEntity<>(batchProductService.getStorageQuantityByProductId(productId), HttpStatus.OK);
    }
}
