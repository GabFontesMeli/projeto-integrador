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
