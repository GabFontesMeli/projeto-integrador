package com.example.projetointegrador.repository;

import com.example.projetointegrador.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
    
}
