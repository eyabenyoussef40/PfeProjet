package com.pfeApp.jira.services;

import com.pfeApp.jira.entities.Admin;
import com.pfeApp.jira.entities.ChefDeProjet;
import com.pfeApp.jira.entities.Membre;
import com.pfeApp.jira.entities.User;
import com.pfeApp.jira.repository.AdminRepo;
import com.pfeApp.jira.repository.ChefDeProjetRepo;
import com.pfeApp.jira.repository.MembreRepo;
import com.pfeApp.jira.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo ur;
    @Autowired
    ChefDeProjetRepo chefDeProjetRepository;

    @Autowired
    AdminRepo adminRepository;

    @Autowired
    MembreRepo membreRepository;

    @Override
    public User addUser(User user) {
        return ur.save(user);
    }

    @Override
    public ChefDeProjet addChefDeProjet(ChefDeProjet chefDeProjet) {
        return chefDeProjetRepository.save(chefDeProjet);
    }

    @Override
    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Membre addMembre(Membre membre) {
        return membreRepository.save(membre);
    }


    @Override
    public void deleteUser(Long id) {
        if (ur.existsById(id)) {
            ur.deleteById(id); // Supprimer un utilisateur
        } else {
            throw new RuntimeException("User not found with id: " + id); // Gestion d'erreur
        }
    }


    @Override
    public User updateUser(Long id, User user) {
        Optional<User> optionalUser = ur.findById(id); // Rechercher l'utilisateur par ID
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get(); // Récupérer l'utilisateur existant

            // Mettre à jour uniquement les champs non nuls
            if (user.getFirstname() != null) {
                existingUser.setFirstname(user.getFirstname());
            }
            if (user.getLastname() != null) {
                existingUser.setLastname(user.getLastname());
            }
            if (user.getUsername() != null) {
                existingUser.setUsername(user.getUsername());
            }
            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getPassword() != null) {
                existingUser.setPassword(user.getPassword());
            }
            if (user.getRole() != null) {
                existingUser.setRole(user.getRole());
            }

            return ur.save(existingUser); // Sauvegarder les modifications
        } else {
            throw new RuntimeException("User not found with id: " + id); // Gestion d'erreur
        }
    }


    @Override
    public Optional<User> findUserById(Long id) {
        return ur.findById(id); // Rechercher un utilisateur par ID
    }

    @Override
    public User getByEmail(String email) {
        return ur.findByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) ur.findAll();
    }


    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UserDetails user = ur.findByEmail(username);
                if (user == null)
                    new UsernameNotFoundException("User not found");
                 return (user);
            }//Implémentation de UserDetailsService pour charger un utilisateur par son email
        };
    }
}








