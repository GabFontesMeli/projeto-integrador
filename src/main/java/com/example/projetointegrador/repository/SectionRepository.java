package com.example.projetointegrador.repository;

import com.example.projetointegrador.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository <Section, Long> {
    
}
