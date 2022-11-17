package com.example.projetointegrador.model;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Float volume;

    @OneToMany(mappedBy = "storage", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("storage")
    private Set<Batch> batches;

    @OneToMany(mappedBy = "storage")
    private Set<Section> sections;

    @ManyToMany(mappedBy = "storages")
    @JsonIgnoreProperties("storages")
    private Set<UserU> users;

}
