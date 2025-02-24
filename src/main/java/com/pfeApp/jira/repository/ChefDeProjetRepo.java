package com.pfeApp.jira.repository;

import com.pfeApp.jira.entities.ChefDeProjet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ChefDeProjetRepo extends JpaRepository<ChefDeProjet,Long> {
}
