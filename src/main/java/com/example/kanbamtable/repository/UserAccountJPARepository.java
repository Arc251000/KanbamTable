package com.example.kanbamtable.repository;

import com.example.kanbamtable.model.entity.UserAccount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountJPARepository extends JpaRepository<UserAccount, Serializable> {

    public Optional<UserAccount> findByEmail(String email);

    public List<UserAccount> findAllByEmailIsLike(String email, Pageable pageable);



}
