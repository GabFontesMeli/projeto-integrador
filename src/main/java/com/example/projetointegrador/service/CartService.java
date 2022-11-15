package com.example.projetointegrador.service;

import com.example.projetointegrador.dto.CartDTO;
import com.example.projetointegrador.dto.CartItemDTO;
import com.example.projetointegrador.dto.CartStatusDTO;
import com.example.projetointegrador.enums.CartStatusEnum;
import com.example.projetointegrador.exceptions.InsufficientStockException;
import com.example.projetointegrador.exceptions.UserUNotFoundException;
import com.example.projetointegrador.model.Cart;
import com.example.projetointegrador.model.CartItem;
import com.example.projetointegrador.model.Product;
import com.example.projetointegrador.model.UserU;
import com.example.projetointegrador.repository.CartRepository;
import com.example.projetointegrador.repository.ProductRepository;
import com.example.projetointegrador.repository.UserRepository;
import com.example.projetointegrador.service.interfaces.ICartService;
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
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Double createCart(CartDTO cartDTO) throws UserUNotFoundException, InsufficientStockException {
        UserU newUser = new UserU();
        newUser.setId(cartDTO.getUserId());

        if(!userRepository.existsById(cartDTO.getUserId())) throw new UserUNotFoundException("user not found");

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

    private List<String> checkProductsQuantity(List<CartItemDTO> cartItemsDTO) {
        List<String> errors = new ArrayList<>();
        Product product = new Product();

        for (CartItemDTO cartItemDTO:cartItemsDTO) {
            product = productRepository.findById(cartItemDTO.getProductId()).orElseThrow();

            if(product.getInventory().getQuantity() < cartItemDTO.getQuantity()) {
                errors.add("product with id " + cartItemDTO.getProductId() + " has insufficient stock");
            }
        }
        return errors;
    }

    private Set<CartItem> setCartItems(CartDTO cartDto, Cart cart) throws InsufficientStockException {
        
        List<CartItemDTO> cartItems = cartDto.getProducts();
        List<String> errors = checkProductsQuantity(cartItems);
        
        if(!errors.isEmpty()) throw new InsufficientStockException("Erros: ", errors);

        Set<CartItem> cartItemList = new HashSet<>();

        for (CartItemDTO cartItemDTO : cartItems) {
            Product product = productRepository.findById(cartItemDTO.getProductId()).orElseThrow();
            CartItem newCartItem = new CartItem(cartItemDTO.getQuantity(), product);
            newCartItem.setCart(cart);
            cartItemList.add(newCartItem);
            Value.total += newCartItem.getValue();
        }

        return cartItemList;
    }
}
