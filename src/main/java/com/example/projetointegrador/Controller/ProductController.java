 package com.example.projetointegrador.Controller;

 import com.example.projetointegrador.model.Product;
 import com.example.projetointegrador.service.ProductService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RestController;

 @RestController("api/v1/product")
 public class ProductController {
     @Autowired
     private ProductService productService;

     @PostMapping
     public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
         return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
     }
 }
