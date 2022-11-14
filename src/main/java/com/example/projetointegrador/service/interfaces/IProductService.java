package com.example.projetointegrador.service.interfaces;

import java.util.List;

import com.example.projetointegrador.exceptions.ProductNotFoundException;
import com.example.projetointegrador.model.Product;

public interface IProductService {
    Product saveProduct(Product product);
    Product findById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    List<Product> getAllProductsBySection(String section);
}
