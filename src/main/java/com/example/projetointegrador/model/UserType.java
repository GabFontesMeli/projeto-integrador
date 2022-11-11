package com.example.projetointegrador.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@NoArgsConstructor
@Getter
@Setter
@Entity
@AllArgsConstructor
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String type;

    @OneToMany(mappedBy = "userType")
    @JsonIgnoreProperties("userType")
    private Set<UserU> users = new HashSet<>();
}
