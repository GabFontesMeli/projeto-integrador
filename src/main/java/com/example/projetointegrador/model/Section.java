package com.example.projetointegrador.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Section {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private Float temperature;

    @ManyToOne
    @JoinColumn(name = "storage_id", referencedColumnName = "id")
    @JsonIgnoreProperties("sections")
    private Storage storage;

//    @OneToMany(mappedBy = "section")
//    private Set<Batch> batches;

    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties("section")
    private Set<Product> products;

    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties("section")
    private Set<BatchProduct> batchProducts;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

}
