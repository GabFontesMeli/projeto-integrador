package com.example.projetointegrador.service;

import com.example.projetointegrador.model.Product;
import com.example.projetointegrador.repository.IProductRepository;
import com.example.projetointegrador.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

}
