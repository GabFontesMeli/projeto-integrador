package com.example.projetointegrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjetoIntegradorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoIntegradorApplication.class, args);
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }

}
