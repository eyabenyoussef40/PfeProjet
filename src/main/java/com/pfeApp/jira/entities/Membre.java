package com.pfeApp.jira.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Membre extends User {

    private Long idMembre;
    @ManyToMany(mappedBy = "membres", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Tache> Taches= new HashSet<>();

}
