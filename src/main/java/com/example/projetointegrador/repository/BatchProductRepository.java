package com.example.projetointegrador.repository;

import com.example.projetointegrador.model.BatchProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BatchProductRepository extends JpaRepository<BatchProduct, Long> {

    @Query(value = "select * from batch_product where product_id = ?1 and quantity >= ?2 limit 1", nativeQuery = true)
    BatchProduct findBatchProductByProductId(Long productId, Integer quantity);
}
