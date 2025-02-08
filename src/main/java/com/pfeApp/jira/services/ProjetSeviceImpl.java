package com.pfeApp.jira.services;

import com.pfeApp.jira.entities.Projet;
import com.pfeApp.jira.repository.ProjetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetSeviceImpl implements ProjetService  {
    @Autowired
    ProjetRepo pr;

    @Override
    public Projet addProjet(Projet projet) {
        return pr.save(projet);
    }

    @Override
    public Projet updateProjet(Projet projet, int id_projet) {
        Projet p=pr.findById(id_projet).get();
        p.setNomProjet(projet.getNomProjet());//modification 3al nom de projet
        p.setDescription(projet.getDescription());
        p.setStatut(projet.getStatut());

        return pr.save(p);
    }
    public Projet getByNomProjet(String nomProjet){
        Projet projet = pr.findByNomProjet(nomProjet);
        return projet ;
    }

    @Override
    public void deleteProjet(int id_projet) {
        pr.deleteById(id_projet);

    }

    @Override
    public List<Projet> getAll() {
        return (List<Projet>) pr.findAll();
    }

    @Override
    public List<Projet> addall(List<Projet> projetall) {
        return (List<Projet>) pr.saveAll(projetall);
    }

    @Override
    public String add(Projet projet) {
        if (pr.existsByNomProjet(projet.getNomProjet())) {
        return "ce projet est déja existé";
        }else {
            pr.save(projet);
        }
        return "add";
    }
}
