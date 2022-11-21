package com.example.projetointegrador.repository;

import com.example.projetointegrador.dto.SaleInfoCartDTO;
import com.example.projetointegrador.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findCartByUserId(Long userId);

    @Query("SELECT c FROM Cart c WHERE c.date BETWEEN ?1 AND ?2 AND c.status = 'CLOSED'")
    List<Cart> findCartsByDateGreaterThanEqualAndDateLessThanEqual(LocalDate startDate, LocalDate endDate);
}
