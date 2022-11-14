package com.example.projetointegrador.service.interfaces;

import com.example.projetointegrador.model.Product;

import java.util.List;

public interface IProductService {
    Product saveProduct(Product product);
    List<Product> getAllProducts();
    List<Product> getAllProductsBySection(String section);
}
