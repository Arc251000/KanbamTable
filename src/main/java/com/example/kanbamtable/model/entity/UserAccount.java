package com.example.kanbamtable.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class UserAccount {
    @Id
    private String email;
    @JsonIgnore
    private String password;
    private String name;
    @JsonIgnore
    private String role;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy= "user")
    private List<Task> tasks;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy= "owner")
    private List<Project> ownedProjects;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Project> joinedProjects;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Notification> notifications;


    public boolean isWorkerOfProject(int id){


        for(Project p: this.getJoinedProjects()){
            if(p.getId()==id)
                return true;
        }

        return false;


    }

    public boolean isOwnerOfProject(int id){


        for(Project p: this.getOwnedProjects()){
            if(p.getId()==id)
                return true;
        }

        return false;


    }

}
