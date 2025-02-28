package com.pfeApp.jira.Configuration;

import com.pfeApp.jira.entities.Role;
import com.pfeApp.jira.entities.User;
import com.pfeApp.jira.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminInitializer {
    @Bean
    CommandLineRunner initAdmin(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            // Vérifier si l'admin existe déjà
            if (userRepo.findByEmail("admin@example.com") == null) {
                User admin = new User();
                admin.setFirstname("Admin");
                admin.setLastname("Admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin123")); // Mot de passe sécurisé
                admin.setRole(Role.ADMIN);
                userRepo.save(admin);
                System.out.println("Admin user created successfully!");
            } else {
                System.out.println("Admin user already exists.");
            }
        };
    }
}
