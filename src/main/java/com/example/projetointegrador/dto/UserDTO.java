package com.example.projetointegrador.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String userType;
}
