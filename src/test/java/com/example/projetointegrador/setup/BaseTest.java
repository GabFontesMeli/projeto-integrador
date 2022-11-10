package com.example.projetointegrador.setup;

import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.model.Section;
import com.example.projetointegrador.model.Storage;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

abstract public class BaseTest {
   protected Batch batch = new Batch();
   protected Section section = new Section();
   protected Storage storage = new Storage();
   protected BatchProduct batchProduct = new BatchProduct();
   protected Set<BatchProduct> batchProducts = new HashSet<>();

    @BeforeEach
    void setup() {

        batch.setId(3L);
        batch.setExpirationDate(LocalDate.parse("2023-01-01"));

        section.setId(1L);
        section.setName("teste1");
        section.setTemperature(3.1f);

        batchProduct.setId(1L);
        batchProduct.setQuantity(10);
        batchProduct.setManufacturingDate(LocalDate.parse("2022-12-10"));
        batchProducts.add(batchProduct);

        storage.setId(1L);
        storage.setVolume(100.0f);

        batch.setSection(section);
        batch.setStorage(storage);
        batch.setBatchProduct(batchProducts);
    }
}
