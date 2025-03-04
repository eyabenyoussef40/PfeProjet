package com.pfeApp.jira.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "taches")
public class Tache {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idTache;
        private String nomTache;
        private String description;
        private Date dateDebut;
        private int dureeExpecte;
        private int dureeReel;
        @Temporal(TemporalType.DATE)
        private Date dateCreation;
        private int idUserCreat;
        private Date derniereModif;
        private int idUserModif;
        @Enumerated(EnumType.STRING)
        private StatutTache statut;
        @Enumerated(EnumType.STRING)
        private Priorite priorite;
        @JsonIgnore
        @ManyToOne()
        @JoinColumn(name="projet_id")
        private Projet projet;
        @JsonIgnore

        @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinTable(
                name = "taches_membre",
                joinColumns = @JoinColumn(name = "idTache"),
                inverseJoinColumns = @JoinColumn(name = "idMembre")
        )
        private Set<Membre> membres = new HashSet<>();



    }
