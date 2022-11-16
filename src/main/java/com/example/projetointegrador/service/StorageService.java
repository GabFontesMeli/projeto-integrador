package com.example.projetointegrador.service;

import com.example.projetointegrador.exceptions.StorageInvalidException;
import com.example.projetointegrador.model.Storage;
import com.example.projetointegrador.repository.StorageRepository;
import com.example.projetointegrador.service.interfaces.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageService implements IStorageService {

    @Autowired
    private StorageRepository storageRepository;

    @Override
    public Storage findById(Long id) throws StorageInvalidException {
        return storageRepository.findById(id).orElseThrow(() -> new StorageInvalidException("storage not found"));
    }
}
