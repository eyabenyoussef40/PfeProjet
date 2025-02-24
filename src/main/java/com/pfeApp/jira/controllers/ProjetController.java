package com.pfeApp.jira.controllers;

import com.pfeApp.jira.entities.Projet;
import com.pfeApp.jira.services.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projet")
public class ProjetController {
    @Autowired
    ProjetService ps;

    @PostMapping("/addProjet")
    public String add(@RequestBody Projet projet) {System.out.println("ghggghg");
        projet.setDateCreation(new Date());
        return ps.add(projet);
    }
    /*@PutMapping("/upProjet/{id_projet}")
    public String upProjet(@RequestBody Projet projet,@PathVariable("id_projet") Long id_projet){
        ps.updateProjet(projet, id_projet);

        return ("project updated");
    }*/
    @PutMapping("/updateP/{id_projet}")
    public ResponseEntity<?> updateProjet(@PathVariable Long id_projet, @RequestBody Projet projet) {
        try {System.out.println("ghggghg"+id_projet);
            Optional<Projet> updatedProjet = ps.updateProjet(projet, id_projet); // Appel de la méthode de modification
            return ResponseEntity.ok(updatedProjet);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage()); // Gestion d'erreur
        }
    }


    @GetMapping("getByNomProjet")//http://localhost:8082/api/projet/getByNomProjet?nomProjet=Projet Gestion
    public Projet getByNomProjet(@RequestParam("nomProjet") String nomProjet){

        return ps.getByNomProjet(nomProjet);
    }
    @DeleteMapping("deleteP/{id_projet}")//dima y7otli projet deleted
    public ResponseEntity<String> deleteTache(@PathVariable Long id_projet) {
        try {
            ps.deleteProjet(id_projet); // Appel de la méthode de suppression
            return ResponseEntity.ok("Project deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage()); // Gestion d'erreur
        }
    }


    /*@DeleteMapping("/delete/{id_projet}")// api/projet/delete/1
    public String deleteProjet(@PathVariable("id_projet")Long id_projet){
        ps.deleteProjet(id_projet);
        return ("project deleted");
    }*/


    @GetMapping("/getAll")
    public List<Projet> getAll(){
        return ps.getAll();
    }







}


