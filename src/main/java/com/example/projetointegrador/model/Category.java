package com.example.projetointegrador.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class Category {

    @Id
    private Long id;

    @Column(nullable = false, length = 200, unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    @OneToMany(mappedBy = "category")
    private Set<Section> sections;
}
