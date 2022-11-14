package com.example.projetointegrador.controller;

import com.example.projetointegrador.dto.CartDTO;
import com.example.projetointegrador.dto.CartStatusDTO;
import com.example.projetointegrador.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fresh-products/orders")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<Double> createCart(@RequestBody CartDTO cartDTO){
         return new ResponseEntity<>(cartService.createCart(cartDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<String> changeCartStatus(@PathVariable Long cartId, @RequestBody CartStatusDTO cartStatusDTO){
        return new ResponseEntity<>(cartService.changeCartStatus(cartId, cartStatusDTO), HttpStatus.OK);
    }
}
