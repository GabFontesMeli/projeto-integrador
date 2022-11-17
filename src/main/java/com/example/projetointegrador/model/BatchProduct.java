package com.example.projetointegrador.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "batch_product")
public class BatchProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "batch_id", referencedColumnName = "id")
    private Batch batch;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnoreProperties("category")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "manufacturing_date" ,nullable = false)
    private LocalDate manufacturingDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"name", "temperature", "storage", "batchProducts", "category", "volume"})
    private Section section;

    @Column(name = "remaining_quantity")
    private Integer remainingQuantity;

    @PrePersist
    private void setup(){
        this.remainingQuantity = this.quantity;
    }
}
