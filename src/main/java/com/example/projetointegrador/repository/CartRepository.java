package com.example.projetointegrador.repository;

import com.example.projetointegrador.dto.SaleInfoCartDTO;
import com.example.projetointegrador.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

import java.time.LocalDate;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findCartByUserId(Long userId);


    @Query("SELECT c FROM Cart c WHERE c.date BETWEEN ?1 AND ?2 AND c.status = 'CLOSED'")
    List<Cart> findCartsByDateGreaterThanEqualAndDateLessThanEqual(LocalDate startDate, LocalDate endDate);

    @Query(value = "select p.name, sum(ci.quantity) from cart ca join cart_item ci on ci.cart_id = ca.id join batch_product bp on bp.id = ci.batch_product_id join product p on p.id = bp.product_id where ca.date between ?1 and ?2 group by p.id", nativeQuery = true)
    List<Object[]> getSalesProductReportByDate(LocalDate start, LocalDate end);

    @Query(value ="select p.name as product_name, sum(ci.quantity) as quantity, u.name as user_name from cart ca join useru u on u.id = ca.user_id join cart_item ci on ci.cart_id = ca.id join batch_product bp on bp.id = ci.batch_product_id join product p on p.id = bp.product_id where ca.date between ?1 and ?2 and u.id = ?3 group by p.id", nativeQuery = true)
    List<Object[]> getSalesProductReportByUserPeriod(LocalDate start, LocalDate end, Long idUser);

}
