package com.pfeApp.jira.controllers;

import com.pfeApp.jira.entities.Projet;
import com.pfeApp.jira.entities.Tache;
import com.pfeApp.jira.entities.User;
import com.pfeApp.jira.services.ProjetService;
import com.pfeApp.jira.services.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/tache")
public class TacheController {
    @Autowired
    TacheService ts;

    @PostMapping("/addTache")//http://localhost:8082/api/tache/addTache
    public Tache add(@RequestBody Tache tache) {
        tache.setDateCreation(new Date());
        return ts.addTache(tache);

    }
    @PutMapping("upTache/{idTache}")
    public ResponseEntity<?> updateTache(@PathVariable Long idTache, @RequestBody Tache tache) {
        try {
            tache.setDerniereModif(new Date());
            Tache updatedTache = ts.updateTache(tache, idTache); // Appel de la méthode de modification
            return ResponseEntity.ok(updatedTache);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage()); // Gestion d'erreur
        }
    }
    /*@PutMapping("upTache/{idTache}")//http://localhost:8082/api/tache/upTache/1
    public String upTache(@RequestBody Tache tache,@PathVariable("idTache")Long idTache){
        tache.setDerniereModif(new Date());
        ts.updateTache(tache,idTache);

        return ("tache modifiée");
    }*/
    @GetMapping("getByIdTache")//http://localhost:8082/api/tache/getByIdTache?idTache=1
    public Tache getByIdTache(@RequestParam("idTache") Long idTache){
        return ts.getByIdTache(idTache);
    }
    /*@DeleteMapping("/delete/{idTache}")//http://localhost:8082/api/tache/delete/1
    public String deleteTache(@PathVariable("idTache")Long idTache) {
        ts.deleteTache(idTache);
        return ("task deleted");
    }*/
    @DeleteMapping("deleteT/{idTache}")
    public ResponseEntity<String> deleteUser(@PathVariable Long idTache) {
        try {
            ts.deleteTache(idTache); // Appel de la méthode de suppression
            return ResponseEntity.ok("task deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage()); // Gestion d'erreur
        }
    }

    @GetMapping("getAll")//http://localhost:8082/api/tache/getAll
    public List<Tache> getAll(){
        return ts.getAll();

    }



}

