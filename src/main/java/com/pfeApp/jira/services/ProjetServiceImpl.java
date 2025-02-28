package com.pfeApp.jira.services;

import com.pfeApp.jira.entities.Projet;
import com.pfeApp.jira.repository.ProjetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetServiceImpl implements ProjetService  {
    @Autowired
    ProjetRepo pr;

    @Override
    public Optional<Projet> addProjet(Projet projet) {/*
        return Optional.of(pr.save(projet));*/
        return null;
    }

    @Override
    public Optional<Projet> updateProjet(Projet projet, Long id_projet) {
        Optional<Projet> optionalProjet = pr.findById(id_projet); // Rechercher PROJET par ID
        if (optionalProjet.isPresent()) {
            Projet existingProjet = optionalProjet.get(); // Récupérer PROJET existant

            // Mettre à jour uniquement les champs non nuls
            if (projet.getNomProjet() != null) {
                existingProjet.setNomProjet(projet.getNomProjet());
            }
            if (projet.getDescription() != null) {
                existingProjet.setDescription(projet.getDescription());
            }
            if (projet.getStatut() != null) {
                existingProjet.setStatut(projet.getStatut());
            }


            return Optional.of(pr.save(existingProjet)); // Sauvegarder les modifications
        } else {
            throw new RuntimeException("Project not found with id: " + id_projet); // Gestion d'erreur
        }

    }


    @Override
    /*public Projet updateProjet(Projet projet, Long id_projet) {
        Projet p=pr.findById(id_projet).get();
        p.setNomProjet(projet.getNomProjet());//modification 3al nom de projet
        p.setDescription(projet.getDescription());
        p.setStatut(projet.getStatut());

        return pr.save(p);
    }*/

    public Projet getByNomProjet(String nomProjet){
        Projet projet = pr.findByNomProjet(nomProjet);
        return projet ;
    }

    @Override
    public void deleteProjet(Long id_projet) {
        pr.deleteById(id_projet);

    }

    @Override
    public List<Projet> getAll() {
        return (List<Projet>) pr.findAll();
    }


    @Override
    public String add(Projet projet) {
        if (pr.existsByNomProjet(projet.getNomProjet())) {
            return "this project is existing";
        }else {
            pr.save(projet);
        }
        return "add";
    }
}
