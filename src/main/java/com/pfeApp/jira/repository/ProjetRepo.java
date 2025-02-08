package com.pfeApp.jira.repository;

import com.pfeApp.jira.entities.Projet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetRepo extends CrudRepository<Projet,Integer> {
    Projet findByNomProjet(String nomProjet);

    boolean existsByNomProjet(String nomProjet);
}
