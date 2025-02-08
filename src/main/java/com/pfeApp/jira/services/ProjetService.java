package com.pfeApp.jira.services;

import com.pfeApp.jira.entities.Projet;
import java.util.List;

public interface ProjetService {
    Projet addProjet(Projet projet);
    Projet updateProjet(Projet projet, int id_projet);
    void deleteProjet(int id_projet);
    List<Projet> getAll();
    List<Projet> addall(List<Projet> projetall);
    String add(Projet projet);
    Projet getByNomProjet(String nomProjet);
}
