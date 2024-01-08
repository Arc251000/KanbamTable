package com.example.kanbamtable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String resume;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String state;

    @ManyToOne(fetch= FetchType.LAZY, cascade =  CascadeType.REFRESH, optional= false)
    private Project project;

    @ManyToOne(fetch= FetchType.LAZY, cascade =  CascadeType.REFRESH, optional= true)
    private UserAccount user;


}
