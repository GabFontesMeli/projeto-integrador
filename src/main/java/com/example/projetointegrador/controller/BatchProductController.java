package com.example.projetointegrador.controller;

import com.example.projetointegrador.dto.ProductInBatchDTO;
import com.example.projetointegrador.dto.ReportBatchProductDTO;
import com.example.projetointegrador.exceptions.ProductNotFoundException;
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

    /**
     * Return a list of products by section that the expiration date is less than the days parameter.
     * @param days Quantity of days to be considered as fresh.
     * @param sectionId Section id to be considered.
     * @return A list of products that are fresh.
     */
    @GetMapping("/due-date")
    public ResponseEntity<ReportBatchProductDTO> getBatchProductExpiring(@RequestParam Long days,
            @RequestParam Long sectionId) {
        return new ResponseEntity<>(batchProductService.getBatchProductExpiring(days, sectionId), HttpStatus.OK);
    }

    /**
     * Return a list of products by category that the expiration date is less than the days parameter and ordered ascendant.
     * @param days Quantity of days to be considered as fresh.
     * @param categoryId Category id to be considered.
     * @param order Order type.
     * @return A list of products that are fresh ordered.
     */
    @GetMapping("/due-date/list")
    public ResponseEntity<ReportBatchProductDTO> getBatchProductExpiringOrdered(@RequestParam Long days,
            @RequestParam Long categoryId, @RequestParam String order) {
        return new ResponseEntity<>(batchProductService.getBatchProductExpiringOrdered(days, categoryId, order),
                HttpStatus.OK);
    }

    /**
     * Return all batches that contains the product id.
     * @param productId Product id to be considered.
     * @return A list of batches that contains the product id and the section and storage where the product is in.
     */
    @GetMapping("/list/{productId}")
    public ResponseEntity<ProductInBatchDTO> findBatchProductsByProductId(@PathVariable Long productId)
            throws ProductNotFoundException {
        return new ResponseEntity<>(batchProductService.findBatchProductsByProductId(productId), HttpStatus.OK);
    }

    /**
     * Return all batches that contains the product id ordered.
     * @param productId Product id to be considered.
     * @param order Order type.
     * @return A list of batches that contains the product id and the section and storage where the product is in, ordered.
     */
    @GetMapping("/list/{productId}/{order}")
    public ResponseEntity<ProductInBatchDTO> findBatchProductsByProductIdOrdered(@PathVariable Long productId,
            @PathVariable String order) throws ProductNotFoundException {
        return new ResponseEntity<>(batchProductService.findBatchProductsByProductIdOrdered(productId, order),
                HttpStatus.OK);
    }
}
