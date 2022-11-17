package com.example.projetointegrador.repository;

import com.example.projetointegrador.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
    
}
