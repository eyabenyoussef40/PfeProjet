package com.pfeApp.jira.services;

import com.pfeApp.jira.entities.Projet;
import com.pfeApp.jira.entities.Tache;
import com.pfeApp.jira.entities.User;
import com.pfeApp.jira.repository.ProjetRepo;
import com.pfeApp.jira.repository.TacheRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class TacheServiceImpl implements TacheService {
    @Autowired
    TacheRepo tr;
    @Autowired
    ProjetRepo pr;

    @Override
    public Tache addTache(Tache tache)  {
        Optional <Projet> projet=pr.findById(tache.getProjet().getId_projet());
        System.out.println("aaa"+tache.getProjet().getId_projet()+"aaa");
        if(projet.isPresent())
        {System.out.println("bbb"+tache.getProjet().getId_projet()+"bbb");
            tache.setProjet(projet.get());
            tache.setDateCreation(new Date());
            return tr.save(tache);

        }else {
            throw new IllegalArgumentException("projet Not found");
        }
    }

    @Override
    public Tache updateTache(Tache tache, Long idTache) {
        Optional<Tache> optionalTache = tr.findById(idTache); // Rechercher tache par ID
        if (optionalTache.isPresent()) {
            Tache existingTache = optionalTache.get(); // Récupérer tache existante

            // Mettre à jour uniquement les champs non nuls
            if (tache.getNomTache() != null) {
                existingTache.setNomTache(tache.getNomTache());
            }
            if (tache.getDescription() != null) {
                existingTache.setDescription(tache.getDescription());
            }
            if (tache.getStatut() != null) {
                existingTache.setStatut(tache.getStatut());
            }


            return tr.save(existingTache); // Sauvegarder les modifications
        } else {
            throw new RuntimeException("task not found with id: " + idTache); // Gestion d'erreur
        }
    }




    /* @Override
     public Tache updateTache(Tache tache, Long idTache) {
         Tache t=tr.findById(idTache).get();
         t.setNomTache(tache.getNomTache());
         t.setDescription(tache.getDescription());
         t.setStatut(tache.getStatut());

         return tr.save(t);
     }*/
    @Override
    public void deleteTache( Tache tache,Long idTache) {
        if (tr.existsById(idTache)) {
            tr.deleteById(idTache); // Supprimer une tache
        } else {
            throw new RuntimeException("task not found with id: " + idTache); // Gestion d'erreur
        }
    }

    @Override
    public Tache getByIdTache(Long idTache) {
        return tr.findByIdTache(idTache);
    }

    @Override
    public void deleteTache(Long idTache) {
        if (tr.existsById(idTache)) {
            tr.deleteById(idTache); // Supprimer une tache
        } else {
            throw new RuntimeException("task not found with id: " + idTache); // Gestion d'erreur
        }


    }

   /*@Override
    public void deleteTache(Long idTache) {
        tr.deleteById(idTache);

    }*/

    @Override
    public List<Tache> getAll() {
        return (List<Tache>) tr.findAll();
    }


}


