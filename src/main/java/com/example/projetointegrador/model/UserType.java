package com.example.projetointegrador.model;

import com.example.projetointegrador.enums.TypeNames;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;


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
