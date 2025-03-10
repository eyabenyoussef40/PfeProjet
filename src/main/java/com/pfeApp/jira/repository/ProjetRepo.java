package com.pfeApp.jira.repository;

import com.pfeApp.jira.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetRepo extends JpaRepository<Projet,Long> {
    Projet findByNomProjet(String nomProjet);

    boolean existsByNomProjet(String nomProjet);
}
