package com.example.projetointegrador.setup;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

abstract public class BaseTest {

    @Autowired
    protected ObjectMapper objectMapper;

    protected Batch batch = new Batch();
    protected Section section = new Section();
    protected Storage storage = new Storage();

    protected BatchProduct batchProductPayload = new BatchProduct();
    protected Set<BatchProduct> batchProductsPayload = new HashSet<>();
    protected Set<BatchProduct> batchProductsResponse = new HashSet<>();
    protected BatchDTO batchDTO = new BatchDTO();

    protected Category category = new Category();
    protected Product product = new Product();

    protected Product productForTest = new Product();

    protected String path = "src/test/java/com/example/projetointegrador/utils";

    protected Set<BatchProduct> batchProductsBuilder(Product product) {
        Set<BatchProduct> batchProducts = new HashSet<>();
        BatchProduct batchProduct = new BatchProduct();
        batchProduct.setProduct(product);
        batchProduct.setQuantity(10);
        batchProduct.setManufacturingDate(LocalDate.parse("2022-12-10"));
        batchProducts.add(batchProduct);

        return batchProducts;
    }

    @BeforeEach
    void setup() {

        batch.setId(1L);

        storage.setId(1L);
        storage.setVolume(100000.0f);

        category.setId(1L);
        category.setName("Fresco");

        section.setId(1L);
        section.setName("Estoque de melancias");
        section.setCategory(category);
        section.setTemperature(3.1f);
        section.setVolume(200.0f);

        product.setId(1L);
        product.setName("Melancia");
        product.setCategory(category);
        product.setVolume(3.1f);

        batchProductPayload.setBatch(batch);
        batchProductPayload.setProduct(product);
        batchProductPayload.setQuantity(10);
        batchProductPayload.setManufacturingDate(LocalDate.parse("2022-10-10"));
        batchProductPayload.setExpirationDate(LocalDate.parse("2022-12-10"));
        batchProductPayload.setSection(section);
        batchProductPayload.setRemainingQuantity(10);
        batchProductsPayload.add(batchProductPayload);

        BatchProduct batchProductResponse = new BatchProduct();
        batchProductResponse.setId(1L);
        batchProductResponse.setQuantity(10);
        batchProductResponse.setManufacturingDate(LocalDate.parse("2022-12-10"));
        batchProductResponse.setExpirationDate(LocalDate.parse("2023-01-01"));
        batchProductsResponse.add(batchProductResponse);

        batch.setStorage(storage);
        batch.setBatchProduct(batchProductsResponse);

        batchDTO.setStorageId(1L);
        batchDTO.setProducts(batchProductsPayload);

        productForTest.setId(3L);
    }
}
