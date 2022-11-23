package com.example.projetointegrador.service;

import com.example.projetointegrador.dto.UserDTO;
import com.example.projetointegrador.exceptions.ProductNotFoundException;
import com.example.projetointegrador.exceptions.UserUNotFoundException;
import com.example.projetointegrador.model.UserU;
import com.example.projetointegrador.repository.UserRepository;
import com.example.projetointegrador.repository.UserTypeRepository;
import com.example.projetointegrador.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepo;

    public UserU saveUser(UserU user) {
        user.setSecretPassword(new BCryptPasswordEncoder().encode(user.getSecretPassword()));
        return userRepo.save(user);
    }

    public boolean existsById(Long userId) {
        return userRepo.existsById(userId);
    }

    public UserU getUserByNameAndPassword(String name, String password) throws UsernameNotFoundException {
        Boolean userExists = userRepo.existsByName(name);

        if (userExists == false){
            throw new UsernameNotFoundException("Invalid id and password");
         }

        Boolean passwordMatches = new BCryptPasswordEncoder().matches(password, userRepo.findByName(name).get().getSecretPassword());

        if (passwordMatches == false){
            throw new UsernameNotFoundException("Invalid id and password");
        }

        UserU user = userRepo.findByName(name).get();
        return user;
      }
    /**
     * Create a list of UserU and cast the data to UserDTO
     * @return List of UserDTO
     */
    @Override
    public List<UserDTO> getUsers() {
        List<UserU> users = userRepo.findAll();

        return users.stream().map(u -> {
            return UserDTO.builder()
                    .id(u.getId())
                    .name(u.getName())
                    .email(u.getEmail())
                    .build();
        }).collect(Collectors.toList());
    }

    /**
     * Find user by id to update only name and email fields
     * @param userId user id referring to the id in the database
     * @param userDTO body with new information
     * @return Return updated user
     * @throws UserUNotFoundException
     */
    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) throws UserUNotFoundException {
        UserU userU = userRepo.findUserUById(userId);

        if(userU == null) throw new UserUNotFoundException("user not found");

        if (userDTO.getName() != null) userU.setName(userDTO.getName());
        if (userDTO.getEmail() != null) userU.setEmail(userDTO.getEmail());

        UserDTO updatedUser = UserDTO.builder()
                .id(userU.getId())
                .name(userU.getName())
                .email(userU.getEmail())
                .build();

        userRepo.save(userU);

        return updatedUser;
    }


    /**
     * Delete user based in userId parameter
     * @param userId user id referring to the id in the database
     * @return no content
     * @throws UserUNotFoundException if user informed not exists
     */
    @Override
    public Void deleteUser(Long userId) throws UserUNotFoundException {

        UserU userU = userRepo.findUserUById(userId);

        if(userU == null) throw new UserUNotFoundException("user not found");

        userRepo.deleteById(userId);

        return null;
    }

    /**
     * Get user based in userId parameter
     * @param userId user id referring to the id in the database
     * @throws UserUNotFoundException
     * @return Return UserDTO
     */
    @Override
    public UserDTO getUserById(Long userId) throws UserUNotFoundException {
        UserU user = userRepo.findUserUById(userId);

        if(user == null) throw new UserUNotFoundException("user not found");

        return UserDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();
    }
}
