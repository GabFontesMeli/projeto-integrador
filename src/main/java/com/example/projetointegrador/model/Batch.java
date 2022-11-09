package com.example.projetointegrador.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate expirationDate;

    @Column(nullable = false)
    private LocalDate manufacturingDate;

    private LocalTime manufacturingTime;

    @ManyToOne()
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    @JsonIgnoreProperties("batches")
    private Section section;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String providerBatchNumber;

    // @Column(nullable = false)
    // private Long product_id;
}
