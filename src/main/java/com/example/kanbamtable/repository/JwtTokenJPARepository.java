package com.example.kanbamtable.repository;

import com.example.kanbamtable.model.entity.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface JwtTokenJPARepository extends JpaRepository<JwtToken,Serializable>{

}
