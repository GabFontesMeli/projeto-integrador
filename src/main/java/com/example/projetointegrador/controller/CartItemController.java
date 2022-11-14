package com.example.projetointegrador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetointegrador.model.CartItem;
import com.example.projetointegrador.service.CartItemService;

@RestController
@RequestMapping("/api/v1/fresh-products/orders")
public class CartItemController {

    @Autowired
    private CartItemService cartService;

   @GetMapping("/{cartId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long cartId){
        return new ResponseEntity<>(cartService.getCartItems(cartId), HttpStatus.OK);
    }
}
