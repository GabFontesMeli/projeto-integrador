package com.example.projetointegrador.model;

import com.example.projetointegrador.dto.BatchDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "storage_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"batches", "sections"})
    private Storage storage;

    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"batches", "storage"})
    private Section section;

    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"batch", "product"})
    Set<BatchProduct> batchProduct = new HashSet<>();

    public Batch(BatchDTO batchDTO) {
        this.expirationDate = batchDTO.getExpirationDate();

        Set<BatchProduct> batchProducts = batchDTO.getProducts();
        for (BatchProduct batchProduct : batchProducts) {
            BatchProduct newBatchProduct = new BatchProduct();
            newBatchProduct.setBatch(this);
            newBatchProduct.setProduct(batchProduct.getProduct());
            newBatchProduct.setQuantity(batchProduct.getQuantity());
            newBatchProduct.setManufacturingDate(batchProduct.getManufacturingDate());
            newBatchProduct.setManufacturingTime(batchProduct.getManufacturingTime());
            this.batchProduct.add(newBatchProduct);
        }
    }

    public void addProducts(Set<BatchProduct> batchProductList) {
        for (BatchProduct batchProduct : batchProductList) {
            BatchProduct newBatchProduct = new BatchProduct();
            newBatchProduct.setBatch(this);
            newBatchProduct.setProduct(batchProduct.getProduct());
            newBatchProduct.setQuantity(batchProduct.getQuantity());
            newBatchProduct.setManufacturingDate(batchProduct.getManufacturingDate());
            newBatchProduct.setManufacturingTime(batchProduct.getManufacturingTime());
            this.batchProduct.add(newBatchProduct);
        }
    }
}
