 package com.example.projetointegrador.controller;

 import com.example.projetointegrador.model.Product;
 import com.example.projetointegrador.service.interfaces.IProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 @RestController
 @RequestMapping("api/v1/fresh-products")
 public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
     }
    
     @GetMapping
     public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
     }

     @GetMapping("/{section}")
     public ResponseEntity<List<Product>> getAllProductsBySection(@PathVariable String section){
        return new ResponseEntity<>(productService.getAllProductsBySection(section), HttpStatus.OK);
     }
 }
