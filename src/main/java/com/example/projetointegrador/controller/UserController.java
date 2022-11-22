package com.example.projetointegrador.controller;


import com.example.projetointegrador.configs.security.JwtGenerator;
import com.example.projetointegrador.model.UserU;
import com.example.projetointegrador.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtGenerator jwtGenerator;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserU user) {
      try {
        if(user.getName() == null || user.getPassword() == null) {
        throw new UsernameNotFoundException("UserName or Password is Empty");
      }
      UserU userData = userService.getUserByNameAndPassword(user.getName(), user.getPassword());
      if(userData == null){
         throw new UsernameNotFoundException("UserName or Password is Invalid");
      }
         return new ResponseEntity<>(jwtGenerator.generateToken(user), HttpStatus.OK);
      } catch (UsernameNotFoundException e) {
         return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
      }
    }

    /**
     * Create a new user based in the User parameter.
     * @param user Information of the user to be created.
     * @return The user created.
     */
    @PostMapping
    public ResponseEntity<UserU> saveUser(@RequestBody UserU user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }
}
