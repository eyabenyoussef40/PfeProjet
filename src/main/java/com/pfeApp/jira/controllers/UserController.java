package com.pfeApp.jira.controllers;

import com.pfeApp.jira.entities.ChefDeProjet;
import com.pfeApp.jira.entities.User;
import com.pfeApp.jira.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService us;

    @PostMapping("/addUser")
    public User add(@RequestBody User user) {
        return us.addUser(user);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            us.deleteUser(id); // Appel de la méthode de suppression
            return ResponseEntity.ok("User deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage()); // Gestion d'erreur
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            User updatedUser = us.updateUser(id, user); // Appel de la méthode de modification
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage()); // Gestion d'erreur
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        Optional<User> user = us.findUserById(id); // Appel de la méthode de recherche par ID
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body("User not found with id: " + id); // Gestion d'erreur
        }
    }

    @GetMapping("/getByEmail")
    public User getByEmail(@RequestParam("email") String email) {
        return us.getByEmail(email);
    }

    @GetMapping("/getAll")
    public List<User> getAll() {
        return us.getAll();
    }

    @PostMapping("/add-chef-de-projet")
    public ResponseEntity<ChefDeProjet> addChefDeProjet(@RequestBody ChefDeProjet chefDeProjet) {
        ChefDeProjet newChef = us.addChefDeProjet(chefDeProjet);
        return ResponseEntity.ok(newChef);
    }
}







