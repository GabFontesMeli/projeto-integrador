package com.example.projetointegrador.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.example.projetointegrador.dto.CartDTO;
import com.example.projetointegrador.enums.CartStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties("products")
    private User user;

    @Column(nullable = false)
    private Double totalValue;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CartStatusEnum status;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"cart", "cartItem"})
    Set<CartItem> cartItems = new HashSet<>();

    public Cart(CartDTO cartDTO) {
        List<CartItem> cartItems = cartDTO.getProducts();

        for (CartItem cartItem : cartItems) {
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(this);
            newCartItem.setProduct(cartItem.getProduct());
            newCartItem.setQuantity(cartItem.getQuantity());
            this.cartItems.add(newCartItem);
        }
    }

    public void addCartItems(List<CartItem> cartItemList) {
        for (CartItem cartItem : cartItemList) {
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(this);
            newCartItem.setProduct(cartItem.getProduct());
            newCartItem.setQuantity(cartItem.getQuantity());
            newCartItem.setValue(cartItem.getProduct().getPrice() * cartItem.getQuantity());
            this.cartItems.add(newCartItem);
        }
    }

    public boolean isOpen() {
        if (getStatus() == CartStatusEnum.OPEN) {
            return true;
        }
        return false;
    }
}
