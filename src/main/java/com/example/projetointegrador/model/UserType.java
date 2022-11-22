package com.example.projetointegrador.model;

import com.example.projetointegrador.enums.TypeNames;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "user_type")
public class UserType implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private TypeNames type;

    @Override
    public String getAuthority() {
        return this.type.toString();
    }
}
