package com.example.projetointegrador.service;

import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.repository.BatchRepository;
import com.example.projetointegrador.service.interfaces.IBatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BatchService implements IBatchService {
    
    @Autowired
    private BatchRepository repository;

    @Override
    public Batch create(Batch batch) {
        if(repository.existsByProviderBatchNumber(batch)) {
            System.out.println("Existent Provider Number Batch");
            return null;
        }
        return repository.save(batch);
    }

    @Override
    public Batch update(Long id, Batch batch) {
        if(!repository.existsById(id)){
            System.out.println("Batch doesn't exists");
            return null;
        }
        return repository.save(batch);
    }
}
