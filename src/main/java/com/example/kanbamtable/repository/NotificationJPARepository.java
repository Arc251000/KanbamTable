package com.example.kanbamtable.repository;

import com.example.kanbamtable.model.entity.Notification;
import com.example.kanbamtable.model.entity.UserAccount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationJPARepository extends JpaRepository<Notification, Serializable> {

    public List<Notification> findAllByUserAccount(UserAccount user, Pageable page);

    public Optional<Notification> findByUserAccountAndUrl(UserAccount user, String url);


}
