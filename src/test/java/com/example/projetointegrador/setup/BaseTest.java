package com.example.projetointegrador.setup;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.model.*;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

abstract public class BaseTest {
   protected Batch batch = new Batch();
   protected Section section = new Section();
   protected Storage storage = new Storage();

   protected BatchProduct batchProductPayload = new BatchProduct();
   protected Set<BatchProduct> batchProductsPayload = new HashSet<>();
   protected Set<BatchProduct> batchProductsResponse = new HashSet<>();
   protected BatchDTO batchDTO = new BatchDTO();

   protected Inventory inventory = new Inventory();

    protected Product productForTest = new Product();

    protected Set<BatchProduct> batchProductsBuilder(Product product) {
        Set<BatchProduct> batchProducts = new HashSet<>();
        BatchProduct batchProduct = new BatchProduct();
        batchProduct.setProduct(product);
        batchProduct.setQuantity(10);
        batchProduct.setManufacturingDate(LocalDate.parse("2022-12-10"));
        batchProduct.setManufacturingTime(LocalTime.parse("11:00:00"));
        batchProducts.add(batchProduct);

        return batchProducts;
    }

    @BeforeEach
    void setup() {

        batch.setId(1L);
        batch.setExpirationDate(LocalDate.parse("2023-01-01"));

        section.setId(1L);
        section.setName("teste1");
        section.setTemperature(3.1f);

        Product product = new Product();
        product.setId(1L);
        batchProductPayload.setProduct(product);
        batchProductPayload.setQuantity(10);
        batchProductPayload.setManufacturingDate(LocalDate.parse("2022-12-10"));
        batchProductPayload.setManufacturingTime(LocalTime.parse("11:00:00"));
        batchProductsPayload.add(batchProductPayload);

        BatchProduct batchProductResponse = new BatchProduct();
        batchProductResponse.setId(1L);
        batchProductResponse.setQuantity(10);
        batchProductResponse.setManufacturingDate(LocalDate.parse("2022-12-10"));
        batchProductResponse.setManufacturingTime(LocalTime.parse("11:00:00"));
        batchProductsResponse.add(batchProductResponse);

        storage.setId(1L);
        storage.setVolume(100.0f);

        batch.setStorage(storage);
        batch.setBatchProduct(batchProductsResponse);

        batchDTO.setStorageId(1L);
        batchDTO.setExpirationDate(LocalDate.parse("2023-01-01"));
        batchDTO.setProducts(batchProductsPayload);

        productForTest.setId(3L);
    }
}
