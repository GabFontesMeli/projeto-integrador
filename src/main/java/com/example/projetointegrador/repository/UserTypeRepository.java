package com.example.projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.projetointegrador.model.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
    
}
