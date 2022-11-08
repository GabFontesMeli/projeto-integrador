package com.example.projetointegrador.service;

import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.repository.BatchRepository;
import com.example.projetointegrador.service.interfaces.IBatchService;
import org.springframework.stereotype.Service;

@Service
public class BatchService implements IBatchService {

    private BatchRepository repository;

    @Override
    public Batch create(Batch batch) {
        return repository.save(batch);
    }

    @Override
    public Batch update(Long id, Batch batch) {
        return repository.save(batch);
    }
}
