package com.example.projetointegrador.setup;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.model.*;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @BeforeEach
    void setup() {

        batch.setId(3L);
        batch.setExpirationDate(LocalDate.parse("2023-01-01"));

        section.setId(1L);
        section.setName("teste1");
        section.setTemperature(3.1f);

        Product product = new Product();
        product.setId(1L);
        batchProductPayload.setProduct(product);
        batchProductPayload.setQuantity(10);
        batchProductPayload.setManufacturingDate(LocalDate.parse("2022-12-10"));
        batchProductsPayload.add(batchProductPayload);

        BatchProduct batchProductResponse = new BatchProduct();
        batchProductResponse.setId(1L);
        batchProductResponse.setQuantity(10);
        batchProductResponse.setManufacturingDate(LocalDate.parse("2022-12-10"));
        batchProductsResponse.add(batchProductResponse);

        storage.setId(1L);
        storage.setVolume(100.0f);

        batch.setSection(section);
        batch.setStorage(storage);
        batch.setBatchProduct(batchProductsResponse);

        batchDTO.setSectionId(1L);
        batchDTO.setStorageId(1L);
        batchDTO.setExpirationDate(LocalDate.parse("2023-01-01"));
        batchDTO.setProducts(batchProductsPayload);

    }
}
