package com.example.kanbamtable.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class News {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String text;
    private String imgUrl;



}
