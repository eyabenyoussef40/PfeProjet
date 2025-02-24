package com.pfeApp.jira.services;
import com.pfeApp.jira.entities.Tache;

import java.util.List;

public interface TacheService {
    Tache addTache(Tache tache);
    Tache updateTache(Tache tache,Long idTache);
    void deleteTache(Tache tache, Long idTache);

    Tache getByIdTache(Long idTache);
    void deleteTache(Long idTache);
    List<Tache>getAll();

}

