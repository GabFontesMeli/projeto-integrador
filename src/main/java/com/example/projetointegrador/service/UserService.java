package com.example.projetointegrador.service;

import com.example.projetointegrador.dto.UserDTO;
import com.example.projetointegrador.model.UserU;
import com.example.projetointegrador.repository.UserRepository;
import com.example.projetointegrador.repository.UserTypeRepository;
import com.example.projetointegrador.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserTypeRepository userTypeRepo;

    public UserU saveUser(UserU user) {
        user.setUserType(userTypeRepo.findById(user.getUserType().getId()).get());
        return userRepo.save(user);
    }

    public boolean existsById(Long userId) {
        return userRepo.existsById(userId);
    }

    @Override
    public List<UserDTO> getUsers() {
        List<UserU> users = userRepo.findAll();

        return users.stream().map(u -> {
            return UserDTO.builder()
                    .id(u.getId())
                    .name(u.getName())
                    .email(u.getEmail())
                    .userType(u.getUserType().getType())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        UserU userU = userRepo.findUserUById(userId);

        if (userDTO.getName() != null) userU.setName(userDTO.getName());
        if (userDTO.getEmail() != null) userU.setEmail(userDTO.getEmail());

        UserDTO updatedUser = UserDTO.builder()
                .id(userU.getId())
                .name(userU.getName())
                .email(userU.getEmail())
                .userType(userU.getUserType().getType())
                .build();

        userRepo.save(userU);

        return updatedUser;
    }
}
