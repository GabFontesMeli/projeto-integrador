package com.example.projetointegrador.model;

import com.example.projetointegrador.dto.BatchDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "storage_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"batches", "sections", "volume", "users"})
    private Storage storage;

    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"batch", "product"}, allowSetters = true)
    Set<BatchProduct> batchProduct = new HashSet<>();

    public Batch(BatchDTO batchDTO) {

        Set<BatchProduct> batchProducts = batchDTO.getProducts();
        for (BatchProduct batchProduct : batchProducts) {
            BatchProduct newBatchProduct = new BatchProduct();
            newBatchProduct.setBatch(this);
            newBatchProduct.setProduct(batchProduct.getProduct());
            newBatchProduct.setQuantity(batchProduct.getQuantity());
            newBatchProduct.setManufacturingDate(batchProduct.getManufacturingDate());
            newBatchProduct.setExpirationDate(batchProduct.getExpirationDate());
            newBatchProduct.setSection(batchProduct.getSection());
            this.batchProduct.add(newBatchProduct);
        }
    }

    public void addProducts(Set<BatchProduct> batchProductList) {
        for (BatchProduct batchProduct : batchProductList) {
            BatchProduct newBatchProduct = new BatchProduct();
            newBatchProduct.setId(batchProduct.getId());
            newBatchProduct.setBatch(this);
            newBatchProduct.setProduct(batchProduct.getProduct());
            newBatchProduct.setQuantity(batchProduct.getQuantity());
            newBatchProduct.setManufacturingDate(batchProduct.getManufacturingDate());
            newBatchProduct.setExpirationDate(batchProduct.getExpirationDate());
            newBatchProduct.setSection(batchProduct.getSection());
            this.batchProduct.add(newBatchProduct);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Batch batch = (Batch) o;
        return id.equals(batch.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
