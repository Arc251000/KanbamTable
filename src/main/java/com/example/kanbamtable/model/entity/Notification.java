package com.example.kanbamtable.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String text;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private boolean accepted;
    @Column(nullable = false)
    private boolean viewed;


    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY, cascade =  CascadeType.REFRESH, optional= false)
    private UserAccount userAccount;



}
