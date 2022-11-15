package com.example.projetointegrador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
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
    @JsonIgnore
    private Set<UserU> users;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private Inventory inventory;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    Set<BatchProduct> batchProduct = new HashSet<>();

    @ManyToOne
    @JsonIgnore
    private Section section;

}
