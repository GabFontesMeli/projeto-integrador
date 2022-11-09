package com.example.projetointegrador.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "userType_id", referencedColumnName = "id")
    @JsonIgnoreProperties("users")
    private UserType userType;

    @ManyToMany
    @JoinTable(
        name = "seller_product",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    @JsonIgnoreProperties("users")
    private Set<Product> products = new HashSet<>();
}
