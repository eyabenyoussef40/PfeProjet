package com.pfeApp.jira.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "Projects")
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_projet;
    private String nomProjet;  // Ensure this is correctly spelled
    private String description;
    private Date dateDebut;
    private int dureeExpecte;
    private int dureeReel;
    private Date dateCreation;
    private String projectType;

    @Enumerated(EnumType.STRING)
    private StatutProjet statut;
}