package com.example.projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projetointegrador.model.Inventory;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsInventoryByProductId(Long productId);

    Inventory findInventoryByProductId(Long productId);
    @Query(value = "SELECT (sum(p.volume) * i.quantity) as volume FROM inventory i " +
            "JOIN product p on i.product_id = p.id " +
            "JOIN section s on s.id = p.section_id " +
            "JOIN storage st on st.id = s.storage_id " +
            "WHERE s.id = 1 " +
            "GROUP BY i.id ", nativeQuery = true)
    List<Float> findVolumeByStorage(Long storageId);

}
