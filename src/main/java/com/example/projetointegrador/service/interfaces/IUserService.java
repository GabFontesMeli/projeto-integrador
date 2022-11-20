package com.example.projetointegrador.service.interfaces;

import com.example.projetointegrador.dto.UserDTO;
import com.example.projetointegrador.model.UserU;

import java.util.List;

public interface IUserService {
    UserU saveUser(UserU user);

    boolean existsById(Long userId);

    List<UserDTO> getUsers();

    UserDTO updateUser(Long userId, UserDTO userDTO);
}
