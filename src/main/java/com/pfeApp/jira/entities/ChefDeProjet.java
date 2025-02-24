package com.pfeApp.jira.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ChefDeProjet extends User {

    private Long idChef;

}
