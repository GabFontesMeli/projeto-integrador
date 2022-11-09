package com.example.projetointegrador.service;

import com.example.projetointegrador.model.User;
import com.example.projetointegrador.repository.IUserRepository;
import com.example.projetointegrador.repository.UserTypeRepository;
import com.example.projetointegrador.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private UserTypeRepository userTypeRepo;

    public User saveUser(User user) {
        user.setUserType(userTypeRepo.findById(user.getUserType().getId()).get());
        return userRepo.save(user);
    }
}
