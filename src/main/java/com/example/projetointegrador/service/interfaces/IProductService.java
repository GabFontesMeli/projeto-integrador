package com.example.projetointegrador.service.interfaces;

import java.util.List;

import com.example.projetointegrador.model.Product;

public interface IProductService {
    Product saveProduct(Product product);
    List<Product> getAllProducts();
}
