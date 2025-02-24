package com.pfeApp.jira.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;
@Data
@Inheritance(strategy = InheritanceType.JOINED)
/*@DiscriminatorColumn(name = "type_utilisateur")*/
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name= "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String firstname;
    private String lastname;
    @Column(unique=true)
    private String username;
    @Column(unique = true,nullable=false)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Date createAt;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Projet> projets = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }//Implémentation de getAuthorities() pour retourner les rôles de l'utilisateur sous forme de GrantedAuthority
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserDetails orElseThrow(Object userNotFound) {
        return null;
    }
}


