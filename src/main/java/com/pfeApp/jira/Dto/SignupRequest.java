package com.pfeApp.jira.Dto;

import com.pfeApp.jira.entities.Role;
import lombok.Data;

import java.util.Date;

@Data
public class SignupRequest {
    private String firstName ;
    private String lastName ;
    private String email ;
    private String password ;
    private String title;
    private int phone;
    private String adress;
    private Date birthday;
    private String Notes;
    private Role role;
}
