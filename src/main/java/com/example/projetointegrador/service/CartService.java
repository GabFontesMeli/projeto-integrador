package com.example.projetointegrador.service;

import com.example.projetointegrador.dto.CartDTO;
import com.example.projetointegrador.dto.CartItemDTO;
import com.example.projetointegrador.dto.CartStatusDTO;
import com.example.projetointegrador.enums.CartStatusEnum;
import com.example.projetointegrador.exceptions.*;
import com.example.projetointegrador.model.*;
import com.example.projetointegrador.repository.CartRepository;
import com.example.projetointegrador.service.interfaces.IBatchProductService;
import com.example.projetointegrador.service.interfaces.ICartService;
import com.example.projetointegrador.service.interfaces.IProductService;
import com.example.projetointegrador.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public Double createCart(CartDTO cartDTO) throws UserUNotFoundException, InsufficientStockException, ProductNotFoundException, ExpiredProductException {
        UserU newUser = new UserU();
        newUser.setId(cartDTO.getUserId());
        double total;

        if(!userService.existsById(cartDTO.getUserId())) throw new UserUNotFoundException("user not found");

        Cart cart = new Cart();
        cart.setDate(LocalDate.now());
        cart.setUser(newUser);
        cart.setStatus(cartDTO.getStatus());

        Set<CartItem> cartItemList = setCartItems(cartDTO, cart);

        total = cartItemList.stream().mapToDouble(CartItem::getItemValue).sum();

        cart.setCartItems(cartItemList);
        cart.setTotalValue(total);

        cartRepository.save(cart);

        return total;
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


    /**
     * Cancels the order by id and changes the cart status to "CANCELED".
     * @param cartId Id of the cart to be canceled.
     * @param userId Id of the user that wants to cancel the order.
     * @return CartStatusDTO object with updated status.
     * @throws CartNotFoundException
     * @throws InvalidUserException
     * @throws ExpiredCancellationPeriodException
     * @throws UnfinishedOrderException
     */
    @Override
    public CartStatusDTO cancelOrder(Long cartId, Long userId) throws CartNotFoundException, InvalidUserException, ExpiredCancellationPeriodException, UnfinishedOrderException {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("order not found"));

        if(!userId.equals(cart.getUser().getId())) {
            throw new InvalidUserException("invalid user");
        }
        if(LocalDate.now().isAfter(cart.getDate().plusDays(7))) {
            throw new ExpiredCancellationPeriodException("cancellation period expired");
        }
        if(cart.getStatus().equals(CartStatusEnum.OPEN)) {
            throw new UnfinishedOrderException("unfinished order");
        }

        for (CartItem cartItem : cart.getCartItems()) {
            Integer remainQuantity = cartItem.getBatchProduct().getRemainingQuantity();
            cartItem.getBatchProduct().setRemainingQuantity(remainQuantity + cartItem.getQuantity());
        }

        cart.setTotalValue(0.00);
        cart.setStatus(CartStatusEnum.CANCELED);
        cartRepository.save(cart);

        CartStatusDTO cartStatusDTO = new CartStatusDTO();
        cartStatusDTO.setStatus(cart.getStatus().toString());

        return cartStatusDTO;
    }

    private List<String> checkProductsQuantity(List<CartItemDTO> cartItemsDTO) {
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

    private Set<CartItem> setCartItems(CartDTO cartDto, Cart cart) throws InsufficientStockException, ProductNotFoundException, ExpiredProductException {
        List<CartItemDTO> cartItems = cartDto.getProducts();
        List<String> errors = checkProductsQuantity(cartItems);

        Set<CartItem> cartItemList = new HashSet<>();
        List<BatchProduct> modifiedProducts = new ArrayList<>();

        for (CartItemDTO cartItemDTO : cartItems) {
            Product product = productService.findById(cartItemDTO.getProductId());
            BatchProduct batchProduct = batchProductService.getBatchProductByProductId(cartItemDTO.getProductId(), cartItemDTO.getQuantity());
            if(batchProduct == null) {
                continue;
            }
            batchProductService.verifyExpirationDate(batchProduct.getExpirationDate());
            CartItem newCartItem = new CartItem(cartItemDTO.getQuantity(), product, batchProduct);
            newCartItem.setCart(cart);
            cartItemList.add(newCartItem);
            batchProduct.setRemainingQuantity(batchProduct.getRemainingQuantity()-cartItemDTO.getQuantity());
            modifiedProducts.add(batchProduct);
        }
        if(!errors.isEmpty()) {
            throw new InsufficientStockException("Erros: ", errors);
        } else {
            batchProductService.saveAll(modifiedProducts);
        }


        return cartItemList;
    }
}