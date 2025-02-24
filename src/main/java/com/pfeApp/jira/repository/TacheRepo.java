package com.pfeApp.jira.repository;

import com.pfeApp.jira.entities.Projet;
import com.pfeApp.jira.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepo extends JpaRepository<Tache,Long> {
    Tache findByIdTache(Long idTache);
}
