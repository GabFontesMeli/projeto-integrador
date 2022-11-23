package com.example.projetointegrador.controller;


import com.example.projetointegrador.configs.security.JwtGenerator;
import com.example.projetointegrador.dto.UserDTO;
import com.example.projetointegrador.exceptions.UserUNotFoundException;
import com.example.projetointegrador.model.UserU;
import com.example.projetointegrador.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private IUserService userService;

    JwtGenerator jwtGenerator = new JwtGenerator();

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserU user) {
      UserU userData = userService.getUserByNameAndPassword(user.getName(), user.getPassword());
        return new ResponseEntity<>(jwtGenerator.generateToken(user), HttpStatus.OK);
    }

    /**
     * Create a new user based in the User parameter.
     * @param user Information of the user to be created.
     * @return The user created.
     */
    @PostMapping("/user")
    public ResponseEntity<UserU> saveUser(@RequestBody UserU user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    /**
     * Get all users from database
     * @return Return all users
     */
    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    /**
     * Get user based in userId parameter
     * @param userId user id referring to the id in the database
     * @throws UserUNotFoundException
     * @return Return user by id
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) throws UserUNotFoundException {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    /**
     * Update user based in userId parameter and data informed by body
     * @param userId user id referring to the id in the database
     * @return Return updated user
     * @throws UserUNotFoundException
     */
    @PutMapping("/user/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) throws UserUNotFoundException {
        return new ResponseEntity<>(userService.updateUser(userId, userDTO), HttpStatus.ACCEPTED);
    }

    /**
     * Delete user based in userId parameter
     * @param userId user id referring to the id in the database
     * @return no content
     * @throws UserUNotFoundException
     */
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws UserUNotFoundException {
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.NO_CONTENT);
    }
}
