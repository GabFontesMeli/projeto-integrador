package com.example.projetointegrador.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Inventory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnoreProperties("inventory")
    private Product product;

    public Inventory(Integer quantity, Long productId) {
        this.quantity = quantity;
        Product product = new Product();
        product.setId(productId);
        this.product = product;
    }

    //private Long product_id;
    //private Long user_id;
}
