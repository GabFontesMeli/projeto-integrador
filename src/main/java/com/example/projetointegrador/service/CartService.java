package com.example.projetointegrador.service;

import com.example.projetointegrador.dto.CartDTO;
import com.example.projetointegrador.dto.CartItemDTO;
import com.example.projetointegrador.dto.CartStatusDTO;
import com.example.projetointegrador.enums.CartStatusEnum;
import com.example.projetointegrador.exceptions.InsufficientStockException;
import com.example.projetointegrador.exceptions.ProductNotFoundException;
import com.example.projetointegrador.exceptions.UserUNotFoundException;
import com.example.projetointegrador.model.*;
import com.example.projetointegrador.repository.BatchProductRepository;
import com.example.projetointegrador.repository.CartRepository;
import com.example.projetointegrador.repository.ProductRepository;
import com.example.projetointegrador.repository.UserRepository;
import com.example.projetointegrador.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Value{
    static Double total = 0.0;
}
@Service
public class CartService implements ICartService{
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private IProductService productService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IBatchProductService batchProductService;

    @Override
    public Double createCart(CartDTO cartDTO) throws UserUNotFoundException, InsufficientStockException, ProductNotFoundException {
        UserU newUser = new UserU();
        newUser.setId(cartDTO.getUserId());

        if(!userService.existsById(cartDTO.getUserId())) throw new UserUNotFoundException("user not found");

        Cart cart = new Cart();
        cart.setDate(cartDTO.getDate());
        cart.setUser(newUser);
        cart.setStatus(cartDTO.getStatus());

        Set<CartItem> cartItemList = setCartItems(cartDTO, cart);

        cart.setCartItems(cartItemList);
        cart.setTotalValue(Value.total);

        cartRepository.save(cart);

        return Value.total;
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

    private List<String> checkProductsQuantity(List<CartItemDTO> cartItemsDTO) throws ProductNotFoundException {
        List<String> errors = new ArrayList<>();
        BatchProduct batchProduct;

        for (CartItemDTO cartItemDTO:cartItemsDTO) {
            batchProduct = batchProductService.getBatchProductByProductId(cartItemDTO.getProductId(), cartItemDTO.getQuantity());

            if(batchProduct == null) {
                errors.add("product with id " + cartItemDTO.getProductId() + " has insufficient stock");
            }
        }
        return errors;
    }

    private Set<CartItem> setCartItems(CartDTO cartDto, Cart cart) throws InsufficientStockException, ProductNotFoundException {

        List<CartItemDTO> cartItems = cartDto.getProducts();
        List<String> errors = checkProductsQuantity(cartItems);

        if(!errors.isEmpty()) throw new InsufficientStockException("Erros: ", errors);

        Set<CartItem> cartItemList = new HashSet<>();

        for (CartItemDTO cartItemDTO : cartItems) {
            BatchProduct batchProduct = batchProductService.getBatchProductByProductId(cartItemDTO.getProductId(), cartItemDTO.getQuantity());
            Product product = productService.findById(cartItemDTO.getProductId());
            CartItem newCartItem = new CartItem(batchProduct.getQuantity(), product, batchProduct);
            newCartItem.setCart(cart);
            cartItemList.add(newCartItem);
            batchProduct.setRemainingQuantity(batchProduct.getRemainingQuantity()-cartItemDTO.getQuantity());
            batchProductService.save(batchProduct);
            Value.total += newCartItem.getValue();
        }

        return cartItemList;
    }
}