package com.example.projetointegrador.service;

import com.example.projetointegrador.exceptions.SectionInvalidException;
import com.example.projetointegrador.model.Section;
import com.example.projetointegrador.repository.SectionRepository;
import com.example.projetointegrador.service.interfaces.ISectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService implements ISectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public Section findById(Long id) throws SectionInvalidException {
        return sectionRepository.findById(id).orElseThrow(() -> new SectionInvalidException("Could not found a section with this id"));

    }
}
