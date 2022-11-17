package com.example.projetointegrador.service.interfaces;

import com.example.projetointegrador.exceptions.SectionInvalidException;
import com.example.projetointegrador.model.Section;

public interface ISectionService {
    Section findById(Long id) throws SectionInvalidException;
}
