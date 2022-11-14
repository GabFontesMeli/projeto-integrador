package com.example.projetointegrador.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.projetointegrador.dto.CartItemDTO;
import com.example.projetointegrador.dto.CartStatusDTO;
import com.example.projetointegrador.enums.CartStatusEnum;
import com.example.projetointegrador.model.Product;
import com.example.projetointegrador.model.User;
import com.example.projetointegrador.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetointegrador.dto.CartDTO;
import com.example.projetointegrador.model.Cart;
import com.example.projetointegrador.model.CartItem;
import com.example.projetointegrador.repository.CartRepository;
import com.example.projetointegrador.service.interfaces.ICartService;

@Service
public class CartService implements ICartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Double createCart(CartDTO cartDTO) {
        User newUser = new User();
        newUser.setId(cartDTO.getUserId());

        Cart cart = new Cart();
        cart.setDate(cartDTO.getDate());
        cart.setUser(newUser);
        cart.setStatus(cartDTO.getStatus());

        List<CartItemDTO> cartItems = cartDTO.getProducts();

        Set<CartItem> cartItemList = new HashSet<>();
        Double totalValue = 0.0;

        for (CartItemDTO cartItemDTO : cartItems) {
            Product product = productRepository.findById(cartItemDTO.getProductId()).orElseThrow();
            CartItem newCartItem = new CartItem(cartItemDTO.getQuantity(), product);
            totalValue += newCartItem.getValue();
            newCartItem.setCart(cart);
            cartItemList.add(newCartItem);
        }

        cart.setCartItems(cartItemList);
        cart.setTotalValue(totalValue);

        cartRepository.save(cart);

        return totalValue;
    }

    @Override
    public String changeCartStatus(Long cartId, CartStatusDTO cartStatusDTO) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();

        if(cartStatusDTO.getStatus().equalsIgnoreCase("OPEN")) {
            cart.setStatus(CartStatusEnum.OPEN);
            cartRepository.save(cart);
        }
        if(cartStatusDTO.getStatus().equalsIgnoreCase("CLOSED")){
            cart.setStatus(CartStatusEnum.CLOSED);
            cartRepository.save(cart);
        }

        return "Cart is " + cart.getStatus();
    }
}
