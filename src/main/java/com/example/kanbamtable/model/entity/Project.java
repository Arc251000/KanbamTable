package com.example.kanbamtable.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Project {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY, cascade =  CascadeType.REFRESH, optional= false)
    private UserAccount owner;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy= "project")
    private List<Task> tasks;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "joinedProjects")
    private List<UserAccount> workers;
}
