package com.example.projetointegrador.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Inventory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "storage_id", referencedColumnName = "id", nullable = false)
    private Storage storage;

    @Column(nullable = false, length = 1000)
    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("inventory")
    private Product product;

    public Inventory(Integer quantity, Long storageId, Long productId) {
        this.quantity = quantity;
        Storage storage = new Storage();
        storage.setId(storageId);
        this.storage = storage;
        Product product = new Product();
        product.setId(productId);
        this.product = product;
    }

    //private Long product_id;
    //private Long user_id;
}
