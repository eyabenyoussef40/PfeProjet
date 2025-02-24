package com.pfeApp.jira.services;

import com.pfeApp.jira.entities.Projet;
import java.util.List;
import java.util.Optional;

public interface ProjetService {
    Optional<Projet> addProjet(Projet projet);
    Optional<Projet> updateProjet(Projet projet, Long id_projet);
    void deleteProjet(Long id_projet);
    List<Projet> getAll();
    String add(Projet projet);
    Projet getByNomProjet(String nomProjet);
}
