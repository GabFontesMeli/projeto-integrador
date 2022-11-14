package com.example.projetointegrador.service;

import com.example.projetointegrador.model.Product;
import com.example.projetointegrador.repository.ProductRepository;
import com.example.projetointegrador.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsBySection(String section) {
        return productRepository.findProductsBySectionName(section);
    }

}
