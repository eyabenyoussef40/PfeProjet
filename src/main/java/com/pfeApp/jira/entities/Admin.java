package com.pfeApp.jira.entities;

import jakarta.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Admin extends User{

    private Long idAdmin;

}
