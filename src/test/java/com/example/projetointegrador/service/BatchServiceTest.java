package com.example.projetointegrador.service;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.exceptions.CategoryInvalidException;
import com.example.projetointegrador.exceptions.InsuficientVolumeException;
import com.example.projetointegrador.exceptions.ProductNotFoundException;
import com.example.projetointegrador.exceptions.SectionInvalidException;
import com.example.projetointegrador.exceptions.StorageInvalidException;
import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.model.Category;
import com.example.projetointegrador.repository.BatchRepository;
import com.example.projetointegrador.repository.SectionRepository;
import com.example.projetointegrador.repository.StorageRepository;
import com.example.projetointegrador.setup.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class BatchServiceTest extends BaseTest {

    @InjectMocks
    private BatchService batchService;

    @Mock
    private BatchRepository batchRepository;

    @Mock
    private StorageService storageService;

    @Mock
    private ProductService productService;

    @Mock
    private SectionService sectionService;

    @Mock
    private StorageRepository storageRepository;

    @Mock
    private BatchProductService batchProductService;

    @Test
    void createBatchShouldReturnBatch() throws StorageInvalidException, SectionInvalidException, ProductNotFoundException, IOException {

        ObjectMapper mp = new ObjectMapper();
        mp.findAndRegisterModules();

        Batch returningBatch = mp.readValue(new File(path + "/responsesBody/createBatchResponse.json"), Batch.class);
        BatchDTO payload = mp.readValue(new File(path + "/requestsBody/createBatchPayload.json"), BatchDTO.class);

        BDDMockito.given(storageService.findById(any(Long.class))).willReturn(storage);
        BDDMockito.given(productService.findById(any(Long.class))).willReturn(product);
        BDDMockito.given(sectionService.findById(any(Long.class))).willReturn(section);
        BDDMockito.given(batchRepository.save(any(Batch.class))).willReturn(returningBatch);
        BDDMockito.given(batchProductService.findVolumeBySection(any(Long.class))).willReturn(0f);

        Batch response = null;
        try {
            response = batchService.createBatch(payload);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThat(mp.writeValueAsString(response)).isEqualTo(mp.writeValueAsString(returningBatch));
    }

    @Test
    void createBatchShouldThrowCategoryInvalidException() throws StorageInvalidException, SectionInvalidException, ProductNotFoundException, IOException {

        ObjectMapper mp = new ObjectMapper();
        mp.findAndRegisterModules();

        BatchDTO payload = mp.readValue(new File(path + "/requestsBody/createBatchPayload.json"), BatchDTO.class);

        Category categoryForTest = new Category();
        category.setId(2L);
        category.setName("Congelados");
        product.setCategory(categoryForTest);

        BDDMockito.given(storageService.findById(any(Long.class))).willReturn(storage);
        BDDMockito.given(productService.findById(any(Long.class))).willReturn(product);
        BDDMockito.given(sectionService.findById(any(Long.class))).willReturn(section);

        assertThrows(CategoryInvalidException.class, () -> batchService.createBatch(payload));
    }

    @Test
    void createBatchShouldThrowInsuficientVolumeException() throws StorageInvalidException, SectionInvalidException, ProductNotFoundException, IOException {

        ObjectMapper mp = new ObjectMapper();
        mp.findAndRegisterModules();

        BatchDTO payload = mp.readValue(new File(path + "/requestsBody/createBatchPayload.json"), BatchDTO.class);

        BDDMockito.given(storageService.findById(any(Long.class))).willReturn(storage);
        BDDMockito.given(productService.findById(any(Long.class))).willReturn(product);
        BDDMockito.given(sectionService.findById(any(Long.class))).willReturn(section);
        BDDMockito.given(batchProductService.findVolumeBySection(any(Long.class))).willReturn(400f);

        assertThrows(InsuficientVolumeException.class, () -> batchService.createBatch(payload));
    }

    @Test
    void updateBatchShouldReturnBatch() throws IOException, SectionInvalidException, ProductNotFoundException {

        ObjectMapper mp = new ObjectMapper();
        mp.findAndRegisterModules();

        List<BatchProduct> listOfBatchProduct = Arrays.asList(mp.readValue(new File(path + "/requestsBody/updateBatchPayload.json"), BatchProduct[].class));
        Set<BatchProduct> payload = Set.copyOf(listOfBatchProduct);


        Batch returnOfFindById = mp.readValue(new File(path + "/responsesBody/createBatchResponse.json"), Batch.class);

        Batch returningBatch = mp.readValue(new File(path + "/responsesBody/updateBatchResponse.json"), Batch.class);

        BDDMockito.given(productService.findById(any(Long.class))).willReturn(product);
        BDDMockito.given(sectionService.findById(any(Long.class))).willReturn(section);
        BDDMockito.given(batchProductService.findVolumeBySection(any(Long.class))).willReturn(0f);
        BDDMockito.given(batchRepository.findById(any(Long.class))).willReturn(Optional.of(returnOfFindById));
        BDDMockito.given(batchRepository.save(any(Batch.class))).willReturn(returningBatch);

        Batch response = null;
        try {
            response = batchService.update(1L, payload);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThat(mp.writeValueAsString(response)).isEqualTo(mp.writeValueAsString(returningBatch));

    }
}
