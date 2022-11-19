 package com.example.projetointegrador.controller;

 import com.example.projetointegrador.model.Product;
 import com.example.projetointegrador.service.interfaces.IProductService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

 import java.util.List;

 @RestController
 @RequestMapping("api/v1/fresh-products")
 public class ProductController {

    @Autowired
    private IProductService productService;

     /**
      * Create a new product based in the Product parameter.
      * @param product Information of the product to be created.
      * @return The product created.
      */
    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
     }

     /**
      * Return all products.
      * @return List of products.
      */
     @GetMapping
     public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
     }

     /**
      * Return the products by section.
      * @param section Section of the products to be returned.
      * @return List of products.
      */
     @GetMapping("/{section}")
     public ResponseEntity<List<Product>> getAllProductsBySection(@PathVariable String section){
        return new ResponseEntity<>(productService.getAllProductsBySection(section), HttpStatus.OK);
     }
 }
