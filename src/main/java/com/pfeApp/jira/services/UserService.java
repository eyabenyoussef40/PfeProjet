package com.pfeApp.jira.services;

import com.pfeApp.jira.entities.Admin;
import com.pfeApp.jira.entities.ChefDeProjet;
import com.pfeApp.jira.entities.Membre;
import com.pfeApp.jira.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.List;
import java.util.Optional;

public interface UserService {

    User addUser(User user);

    ChefDeProjet addChefDeProjet(ChefDeProjet chefDeProjet);

    Admin addAdmin(Admin admin);

    Membre addMembre(Membre membre);

    void deleteUser(Long id);
    User updateUser(Long id, User user);
    Optional<User> findUserById(Long id);
    User getByEmail(String email);
    List<User> getAll();

    /*@Override
        public User loadUserByUsername(String username) throws UsernameNotFoundException {
            User user=ur.findByUsername(username);
            if (user !=null) {
                User Useer = null;
                var springUser = Useer.withUsername(User.getUsername())
                        .password(User.getPassword())
                        .role(User.getRole())
                        .build();
                return springUser;

            }
            return null;
        }*/


    UserDetailsService userDetailsService();



}

