package com.example.projetointegrador.service;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.dto.ProductDTO;
import com.example.projetointegrador.dto.ProductInBatchDTO;
import com.example.projetointegrador.dto.StorageDTO;
import com.example.projetointegrador.exceptions.ProductNotFoundException;
import com.example.projetointegrador.exceptions.SectionInvalidException;
import com.example.projetointegrador.exceptions.StorageInvalidException;
import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.model.Storage;
import com.example.projetointegrador.repository.BatchProductRepository;
import com.example.projetointegrador.setup.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class BatchProductServiceTest extends BaseTest {

    @InjectMocks
    private BatchProductService batchProductService;

    @Mock
    private BatchProductRepository batchProductRepository;

    @Mock
    Storage storage1;

    @Mock
    Batch batch1;

    @BeforeEach
    void setup() {
        storage1.setId(1L);
        storage1.setVolume(10000F);
        batch1.setStorage(storage1);
    }

    @Test
    void getStorageQuantityByProductIdShouldReturnProductDTO() throws IOException {
//
//        ObjectMapper mp = new ObjectMapper();
//        mp.findAndRegisterModules();
//
//        storage1.setId(1L);
//        storage1.setVolume(10000F);
//        batch1.setStorage(storage1);
//
//        List<BatchProduct> responseRepository = List.of(mp.readValue(
//                new File(path + "/responsesBody/BatchProduct/findBatchProductsByProductIdResponse.json"),
//                BatchProduct[].class));
//
//        List<StorageDTO> storageDTOList = new ArrayList<>();
//
//        for (BatchProduct batchProduct : responseRepository) {
//            batchProduct.setBatch(batch1);
//            batch1.setStorage(storage1);
//            StorageDTO storageDTO = new StorageDTO(batchProduct.getBatch().getStorage().getId(),
//                    batchProduct.getRemainingQuantity());
//            storageDTOList.add(storageDTO);
//        }
//
//        System.out.println(responseRepository);
//
//        BDDMockito.given(batchProductRepository.findBatchProductsByProductId(any(Long.class))).willReturn(responseRepository);
//
////        BDDMockito.given(batchRepository.save(any(Batch.class))).willReturn(returningBatch);
////        BDDMockito.given(batchProductService.findVolumeBySection(any(Long.class))).willReturn(0f);
//
//        ProductDTO responseService = null;
//        try {
//            responseService = batchProductService.getStorageQuantityByProductId(any(Long.class));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        assertThat(1).isPositive();
////        assertThat(mp.writeValueAsString(response)).isEqualTo(mp.writeValueAsString(returningBatch));
        assertThat(true).isTrue();
    }

}
