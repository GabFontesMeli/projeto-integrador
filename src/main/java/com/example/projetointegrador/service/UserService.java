package com.example.projetointegrador.service;

import com.example.projetointegrador.model.UserU;
import com.example.projetointegrador.repository.UserRepository;
import com.example.projetointegrador.repository.UserTypeRepository;
import com.example.projetointegrador.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
