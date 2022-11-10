package com.example.projetointegrador.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.example.projetointegrador.dto.BatchDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToOne()
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"batches", "storage"})
    private Section section;

    // @OneToMany(mappedBy = "batch")
    // @JsonIgnoreProperties({"batch", "inventory", "users"})
    // private Set<Product> products = new HashSet<>();

    // @ManyToMany
    // @JoinTable(
    //     name = "batch_product",
    //     joinColumns = @JoinColumn(name = "batch_id", referencedColumnName = "id"),
    //     inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    // )
    // @JsonIgnoreProperties("batches")
    // private Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"batch", "product"})
    Set<BatchProduct> batchProduct = new HashSet<>();

    public Batch(BatchDTO batchDTO) {
        this.expirationDate = batchDTO.getExpirationDate();

        List<BatchProduct> batchProducts = batchDTO.getBatchProduct();
        for (BatchProduct batchProduct : batchProducts) {
            BatchProduct newBatchProduct = new BatchProduct();
            newBatchProduct.setBatch(this);
            newBatchProduct.setProduct(batchProduct.getProduct());
            newBatchProduct.setQuantity(batchProduct.getQuantity());
            newBatchProduct.setManufacturingDate(batchProduct.getManufacturingDate());
            newBatchProduct.setManufacturingTime(batchProduct.getManufacturingTime());
            this.batchProduct.add(newBatchProduct);
        }
        // newBatchProduct.setQuantity(batchDTO.getQuantity());
        // newBatchProduct.setManufacturingDate(batchDTO.getManufacturingDate());
        // newBatchProduct.setManufacturingTime(batchDTO.getManufacturingTime());
        // Product newProduct = new Product();
        // newProduct.setId(batchDTO.getProductId());
        // newBatchProduct.setProduct(newProduct);
        // this.batchProduct.add(newBatchProduct);

        Section section = new Section();
        section.setId(batchDTO.getSectionId());
        this.section = section;

    }
}
