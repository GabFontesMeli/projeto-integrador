//package com.example.projetointegrador.service;
//
//import com.example.projetointegrador.dto.BatchDTO;
//import com.example.projetointegrador.model.Batch;
//import com.example.projetointegrador.repository.BatchRepository;
//import com.example.projetointegrador.setup.BaseTest;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.BDDMockito;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.hamcrest.Matchers.any;
//
//@ExtendWith(MockitoExtension.class)
//public class BatchServiceTest extends BaseTest {
//    @InjectMocks
//    private BatchService batchService;
//    @Mock
//    private BatchRepository batchRepository;
//    @Test
//    void createBatchShoulReturnBatch() {
//        BDDMockito.given(batchRepository.save(any(Batch.class))).willReturn(batch);
//        BatchDTO batchDTO = new BatchDTO();
//        Batch result = batchService.createBatch(batchDTO);
//    }
//}
