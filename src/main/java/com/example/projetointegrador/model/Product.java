package com.example.projetointegrador.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private Double price;

    @Column(length = 200, nullable = false)
    private Float volume;

    @ManyToMany(mappedBy = "products")
    @JsonIgnoreProperties("products")
    private Set<User> users;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("product")
    private Inventory inventory;

    // @ManyToOne
    // @JoinColumn(name = "batch_id", referencedColumnName = "id")
    // @JsonIgnoreProperties("products")
    // private Batch batch;

    // @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
    // @JsonIgnoreProperties("products")
    // private Set<Batch> batches;

    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties({"batch", "product"})
    Set<BatchProduct> batchProduct = new HashSet<>();

     //TODO: relation with category

}
