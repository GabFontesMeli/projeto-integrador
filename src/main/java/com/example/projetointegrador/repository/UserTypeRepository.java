package com.example.projetointegrador.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.projetointegrador.model.UserType;

public interface UserTypeRepository extends CrudRepository<UserType, Long> {
    
}
