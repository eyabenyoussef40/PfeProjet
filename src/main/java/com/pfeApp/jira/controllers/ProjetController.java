package com.pfeApp.jira.controllers;

import com.pfeApp.jira.entities.Projet;
import com.pfeApp.jira.services.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projet")
public class ProjetController {
    @Autowired
    ProjetService ps;

    @GetMapping("/test")
    public String test(){return"waaa";}

    @PostMapping("/addProjet")
    public Projet add(@RequestBody Projet projet) {
        return ps.addProjet(projet);
    }
    @PutMapping("/upProjet/{id_projet}")
    public String upProjet(@RequestBody Projet projet,@PathVariable("id_projet") int id_projet){
        String msg="";
        ps.updateProjet(projet, id_projet);
        msg="projet update ";
        return msg;
    }
    @GetMapping("getByNomProjet")
    public Projet getByNomProjet(@RequestParam("nomProjet") String nomProjet){
        return ps.getByNomProjet(nomProjet);
    }
    @DeleteMapping("/delete{id_projet}")// api/projet/delete/1
    public String deleteProjet(@PathVariable("id_projet")int id_projet){
        String msg="";
        ps.deleteProjet(id_projet);
        msg="delete projet";
        return msg;
    }
    @GetMapping("/getAll")
    public List<Projet> getAll(){
        return ps.getAll();
    }
    @PostMapping("addall")
    public List<Projet>addall(@RequestBody List<Projet>projets){
        return ps.addall(projets);
    }
    @PostMapping("/addProjet2")
    public String add2(@RequestBody Projet projet) {
        return ps.add(projet);
    }






    }


