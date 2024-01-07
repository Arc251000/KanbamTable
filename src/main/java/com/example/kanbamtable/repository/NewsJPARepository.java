package com.example.kanbamtable.repository;

import com.example.kanbamtable.model.entity.News;
import com.example.kanbamtable.model.entity.UserAccount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface NewsJPARepository extends JpaRepository<News, Serializable> {

}
