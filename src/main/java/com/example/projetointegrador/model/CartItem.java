package com.example.projetointegrador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "batch_product_id", referencedColumnName = "id")
    @JsonIgnoreProperties("volume")
    private BatchProduct batchProduct;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "item_value", nullable = false)
    private Double itemValue;

    public CartItem(Integer incomingQuantity, Product incomingProduct, BatchProduct batchProduct) {
        this.batchProduct = batchProduct;
        this.quantity = incomingQuantity;
        this.itemValue = incomingProduct.getPrice() * incomingQuantity;
    }
}
