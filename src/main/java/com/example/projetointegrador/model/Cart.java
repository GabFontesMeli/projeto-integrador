package com.example.projetointegrador.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.example.projetointegrador.dto.CartDTO;
import com.example.projetointegrador.dto.CartItemDTO;
import com.example.projetointegrador.enums.CartStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
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

    public boolean isOpen() {
        if (getStatus() == CartStatusEnum.OPEN) {
            return true;
        }
        return false;
    }
}
