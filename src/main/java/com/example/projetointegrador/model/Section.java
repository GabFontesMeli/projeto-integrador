package com.example.projetointegrador.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToMany(mappedBy = "section")
    private Set<Batch> batches;

    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties("section")
    private Set<Product> products;
}
