package com.pfeApp.jira.repository;

import com.pfeApp.jira.entities.Role;
import com.pfeApp.jira.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String Username);


    User findByEmail(String email);
    User findByRole(Role role);
}
