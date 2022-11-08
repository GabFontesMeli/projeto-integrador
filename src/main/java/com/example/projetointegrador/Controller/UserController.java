package com.example.projetointegrador.Controller;


import com.example.projetointegrador.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
    public ResponseEntity<User> saveUser(User user) {
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
