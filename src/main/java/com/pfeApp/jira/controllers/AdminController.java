package com.pfeApp.jira.controllers;

import com.pfeApp.jira.Dto.SignupRequest;
import com.pfeApp.jira.entities.Projet;
import com.pfeApp.jira.entities.Tache;
import com.pfeApp.jira.entities.User;
import com.pfeApp.jira.services.AuthenticationService;
import com.pfeApp.jira.services.ProjetService;
import com.pfeApp.jira.services.TacheService;
import com.pfeApp.jira.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {


    //gestion des utilisateurs
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    // Récupérer un utilisateur par son ID  http://localhost:8080/api/v1/admin/users/1
    @GetMapping("/users/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body("Utilisateur non trouvé avec l'ID : " + id);
        }
    }

    // Récupérer un utilisateur par son email  http://localhost:8080/api/v1/admin/users/by-email?email=john.doe@example.com
    @GetMapping("/users/by-email")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        User user = userService.getByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(404).body("Utilisateur non trouvé avec l'email : " + email);
        }
    }


    //gestion des projets
    private final ProjetService projetService;

    @GetMapping("/projets")
    public ResponseEntity<List<Projet>> getAllProjets() {
        return ResponseEntity.ok(projetService.getAll());
    }

    @PostMapping("/addProjet")
    public ResponseEntity<String> addProjet(@RequestBody Projet projet) {
        return ResponseEntity.ok(projetService.add(projet));
    }

    @PutMapping("/updateProjet/{id}")
    public ResponseEntity<Projet> updateProjet(@PathVariable Long id, @RequestBody Projet projet) {
        return ResponseEntity.ok(projetService.updateProjet(projet, id).orElseThrow(() -> new RuntimeException("Project not found")));
    }

    @DeleteMapping("/deleteProjet/{id}")
    public ResponseEntity<String> deleteProjet(@PathVariable Long id) {
        projetService.deleteProjet(id);
        return ResponseEntity.ok("Project deleted successfully");
    }
    @GetMapping("/projets/by-nom")//http://localhost:8080/api/v1/admin/projets/by-nom?nomProjet=Projet1
    public ResponseEntity<?> getByNomProjet(@RequestParam String nomProjet) {
        Projet projet = projetService.getByNomProjet(nomProjet);
        if (projet != null) {
            return ResponseEntity.ok(projet);
        } else {
            return ResponseEntity.status(404).body("Projet non trouvé avec le nom : " + nomProjet);
        }
    }


    //gestion des Taches
    private final TacheService tacheService;


    @GetMapping("/taches")
    public ResponseEntity<List<Tache>> getAllTaches() {
        return ResponseEntity.ok(tacheService.getAll());
    }
    @PostMapping("/addTache")
    public ResponseEntity<Tache> addUser(@RequestBody Tache tache) {
        return ResponseEntity.ok(tacheService.addTache(tache));
    }
    @PutMapping("/updateTache/{id}")
    public ResponseEntity<Tache> updateTache(@PathVariable Long id, @RequestBody Tache tache) {
        return ResponseEntity.ok(tacheService.updateTache(tache, id));
    }

    @DeleteMapping("/deleteTache/{id}")
    public ResponseEntity<String> deleteTache(@PathVariable Long id) {
        tacheService.deleteTache(id);
        return ResponseEntity.ok("Tâche supprimée avec succès");
    }

    // Récupérer une tâche par son ID
    @GetMapping("/taches/{id}")
    public ResponseEntity<?> getByIdTache(@PathVariable Long id) {
        Tache tache = tacheService.getByIdTache(id);
        if (tache != null) {
            return ResponseEntity.ok(tache);
        } else {
            return ResponseEntity.status(404).body("Tâche non trouvée avec l'ID : " + id);
        }

}



}




   /* @GetMapping
    public ResponseEntity<String> sayHello(){return ResponseEntity.ok("Hi Admin");

    }*/

    /*private final AuthenticationService authenticationService ;
    @PostMapping("/AddUser")
    public ResponseEntity<User> AddUser(@RequestBody SignupRequest signupRequest){
        return ResponseEntity.ok(authenticationService.signup(signupRequest));*/

