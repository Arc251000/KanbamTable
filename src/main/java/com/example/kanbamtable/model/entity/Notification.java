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
    private String text;
    private String url;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY, cascade =  CascadeType.REFRESH, optional= false)
    private UserAccount userAccount;



}
