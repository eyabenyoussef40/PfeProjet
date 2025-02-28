package com.pfeApp.jira.services;

import com.pfeApp.jira.entities.Projet;
import com.pfeApp.jira.entities.Tache;
import com.pfeApp.jira.entities.User;
import com.pfeApp.jira.repository.ProjetRepo;
import com.pfeApp.jira.repository.TacheRepo;
import com.pfeApp.jira.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TacheServiceImpl implements TacheService {
    @Autowired
    TacheRepo tr;
    @Autowired
    ProjetRepo pr;
    @Autowired
    UserRepo userRepo;

    @Override
    public Tache addTache(Tache tache)  {
        Optional <Projet> projet=pr.findById(tache.getProjet().getId_projet());
        if(projet.isPresent())
        {
            tache.setProjet(projet.get());
            tache.setDateCreation(new Date());
            return tr.save(tache);

        }else {
            throw new IllegalArgumentException("projet Not found");
        }
    }

    /*@Override
    public Tache addTache(Tache tache) {
        // Récupérer l'utilisateur connecté (chef de projet)
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User chefDeProjet = userRepo.findByEmail(email);

        // Vérifier si le chef de projet est associé au projet de la tâche
        Projet projet = tache.getProjet();
        if (projet != null && projet.getUsers().contains(chefDeProjet)) {
            return tr.save(tache);
        } else {
            throw new RuntimeException("Le chef de projet n'est pas associé à ce projet");
        }
    }*/

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

    /*@Override
    public Tache updateTache(Tache tache, Long id) {
        // Récupérer l'utilisateur connecté (chef de projet)
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User chefDeProjet = userRepo.findByEmail(email);

        // Vérifier si le chef de projet est associé au projet de la tâche
        Tache existingTache = tr.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));
        Projet projet = existingTache.getProjet();
        if (projet != null && projet.getUsers().contains(chefDeProjet)) {
            existingTache.setNomTache(tache.getNomTache());
            existingTache.setDescription(tache.getDescription());
            existingTache.setStatut(tache.getStatut());
            return tr.save(existingTache);
        } else {
            throw new RuntimeException("Le chef de projet n'est pas associé à ce projet");
        }
    }*/




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


    /*@Override
    public void deleteTache(Long id) {
        // Récupérer l'utilisateur connecté (chef de projet)
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User chefDeProjet = userRepo.findByEmail(email);

        // Vérifier si le chef de projet est associé au projet de la tâche
        Tache tache = tr.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));
        Projet projet = tache.getProjet();
        if (projet != null && projet.getUsers().contains(chefDeProjet)) {
            tr.deleteById(id);
        } else {
            throw new RuntimeException("Le chef de projet n'est pas associé à ce projet");
        }
    }*/

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


