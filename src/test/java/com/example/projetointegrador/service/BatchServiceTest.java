package com.example.projetointegrador.service;

import com.example.projetointegrador.repository.BatchRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BatchServiceTest {

    @InjectMocks
    private BatchService batchService;

    @Mock
    private BatchRepository batchRepository;


}
