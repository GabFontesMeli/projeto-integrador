package com.example.projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projetointegrador.model.Storage;

public interface StorageRepository extends JpaRepository<Storage, Long> {
    
}
