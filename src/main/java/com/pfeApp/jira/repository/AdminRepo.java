package com.pfeApp.jira.repository;

import com.pfeApp.jira.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepo extends JpaRepository<Admin,Long> {
}
