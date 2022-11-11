package com.example.projetointegrador.repository;

import com.example.projetointegrador.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsBySectionName(String section);
}
