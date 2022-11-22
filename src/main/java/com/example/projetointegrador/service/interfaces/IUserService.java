package com.example.projetointegrador.service.interfaces;

import com.example.projetointegrador.model.UserU;

public interface IUserService {
    UserU saveUser(UserU user);

    boolean existsById(Long userId);

    UserU getUserByNameAndPassword(String name, String password);
}
