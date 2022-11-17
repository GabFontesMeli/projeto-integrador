package com.example.projetointegrador.service.interfaces;

import com.example.projetointegrador.exceptions.StorageInvalidException;
import com.example.projetointegrador.model.Storage;

public interface IStorageService {

    Storage findById(Long id) throws StorageInvalidException;
}
