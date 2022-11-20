package com.example.projetointegrador.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.projetointegrador.model.BatchProduct;

public interface BatchProductRepository extends JpaRepository<BatchProduct, Long> {

    @Query(value = "select * from batch_product where product_id = ?1 and remaining_quantity >= ?2 limit 1", nativeQuery = true)
    BatchProduct findBatchProductByProductIdAndRemainingQuantity(Long productId, Integer quantity);

    @Query(value = "select sum(p.volume * bp.remaining_quantity) from batch_product bp " +
            "Join product p on p.id = bp.product_id " +
            "join section s on s.id = bp.section_id " +
            "where s.id = ?1 group by s.id", nativeQuery = true)
    List<Float> findVolumeBySectionId(Long sectionId);

    List<BatchProduct> findBatchProductsByProductId(Long productId);

    @Query(value = "select * from batch_product where expiration_date <= ?1 and section_id = ?2", nativeQuery = true)
    List<BatchProduct> findBatchProductByExpirationDateAndSectionId(LocalDate date, Long sectionId);

    @Query(value = "select * from batch_product bp " +
            "join product p on bp.product_id = p.id " +
            "where expiration_date <= ?1 " +
            "and p.category_id = ?2 " +
            "order by bp.expiration_date", nativeQuery = true)
    List<BatchProduct> findBatchProductByExpirationOrdered(LocalDate date, Long categoryId);
    Optional<BatchProduct> findBatchProductByProductIdAndBatchId(Long productId, Long batchId);
}
