package com.example.kanbamtable.repository;

import com.example.kanbamtable.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;

@Repository
public interface TaskJPARepository extends JpaRepository<Task, Serializable> {

}
