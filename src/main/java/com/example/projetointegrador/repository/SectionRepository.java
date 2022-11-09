package com.example.projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projetointegrador.model.Section;

public interface SectionRepository extends JpaRepository <Section, Long> {
    
}
