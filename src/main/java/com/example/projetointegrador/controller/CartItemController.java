package com.example.projetointegrador.controller;

import com.example.projetointegrador.model.CartItem;
import com.example.projetointegrador.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/orders")
public class CartItemController {

    @Autowired
    private CartItemService cartService;

    /**
     * Return all cart items.
     * @param cartId Id of the cart to be returned.
     * @return List of cart items.
     */
   @GetMapping("/{cartId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long cartId){
        return new ResponseEntity<>(cartService.getCartItems(cartId), HttpStatus.OK);
    }

    @PutMapping("/discount/{discount}")
    public ResponseEntity<String> discountOnCartItems(@PathVariable Double discount) {
        return new ResponseEntity<>(cartService.discountOnCartItems(discount), HttpStatus.OK);
    }
}
