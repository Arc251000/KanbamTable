package com.example.kanbamtable.repository;

import com.example.kanbamtable.model.entity.Project;
import com.example.kanbamtable.model.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface ProjectJPARepository extends JpaRepository<Project, Serializable> {

    public Optional<Project> findByIdAndOwner(int id, UserAccount owner);

}
