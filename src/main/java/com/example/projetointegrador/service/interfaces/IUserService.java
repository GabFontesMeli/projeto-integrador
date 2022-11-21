package com.example.projetointegrador.service.interfaces;

<<<<<<< HEAD
import com.example.projetointegrador.dto.UserDTO;
import com.example.projetointegrador.exceptions.ProductNotFoundException;
import com.example.projetointegrador.exceptions.UserUNotFoundException;
=======
>>>>>>> master
import com.example.projetointegrador.model.UserU;

import java.util.List;

public interface IUserService {
    UserU saveUser(UserU user);

    boolean existsById(Long userId);

    List<UserDTO> getUsers();

    UserDTO updateUser(Long userId, UserDTO userDTO) throws ProductNotFoundException, UserUNotFoundException;

    Void deleteUser(Long userId) throws UserUNotFoundException;

    UserDTO getUserById(Long userId);
}
