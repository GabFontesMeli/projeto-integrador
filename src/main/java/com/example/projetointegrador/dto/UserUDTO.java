package com.example.projetointegrador.dto;

import com.example.projetointegrador.model.UserU;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUDTO {

    private String name;
    
    public UserUDTO (UserU user) {
        this.name = user.getName();
    }
}
