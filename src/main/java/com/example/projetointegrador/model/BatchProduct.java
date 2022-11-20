package com.example.projetointegrador.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JsonIgnoreProperties("batchProduct")
    private Batch batch;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnoreProperties("category")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "manufacturing_date", nullable = false)
    private LocalDate manufacturingDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    @JsonIgnoreProperties({ "batchProducts",
            "volume",
            "users",
            "name",
            "temperature",
            "storage",
            "category"
    })
    private Section section;

    @Column(name = "remaining_quantity")
    private Integer remainingQuantity;

    @PrePersist
    private void setup() {
        this.remainingQuantity = this.quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatchProduct that = (BatchProduct) o;
        return Objects.equals(id, that.id) && batch.equals(that.batch) && product.equals(that.product) && Objects.equals(section, that.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, batch, product, section);
    }

    @Override
    public String toString() {
        return "BatchProduct{" +
                "id=" + id +
                ", batch=" + batch +
                ", product=" + product +
                ", quantity=" + quantity +
                ", manufacturingDate=" + manufacturingDate +
                ", expirationDate=" + expirationDate +
                ", section=" + section +
                ", remainingQuantity=" + remainingQuantity +
                '}';
    }
}
