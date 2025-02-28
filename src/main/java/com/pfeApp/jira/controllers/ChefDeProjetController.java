package com.pfeApp.jira.controllers;

import com.pfeApp.jira.entities.Tache;
import com.pfeApp.jira.services.TacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/chef-de-projet")
@RequiredArgsConstructor
public class ChefDeProjetController {

    private final TacheService tacheService;

    // Récupérer toutes les tâches du projet du chef
    @GetMapping("/taches")
    public ResponseEntity<List<Tache>> getAllTaches() {
        return ResponseEntity.ok(tacheService.getAll());
    }

    // Ajouter une tâche
    @PostMapping("/addTache")
    public ResponseEntity<Tache> addTache(@RequestBody Tache tache) {
        return ResponseEntity.ok(tacheService.addTache(tache));
    }

    // Modifier une tâche
    @PutMapping("/updateTache/{id}")
    public ResponseEntity<Tache> updateTache(@PathVariable Long id, @RequestBody Tache tache) {
        return ResponseEntity.ok(tacheService.updateTache(tache, id));
    }

    // Supprimer une tâche
    @DeleteMapping("/deleteTache/{id}")
    public ResponseEntity<String> deleteTache(@PathVariable Long id) {
        tacheService.deleteTache(id);
        return ResponseEntity.ok("Tâche supprimée avec succès");
    }
}

