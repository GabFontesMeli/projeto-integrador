package com.example.projetointegrador.repository;

import com.example.projetointegrador.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {

    boolean existsByProviderBatchNumber(Batch batch);
    
}
