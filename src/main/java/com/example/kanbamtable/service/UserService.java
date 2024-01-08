package com.example.kanbamtable.service;

import com.example.kanbamtable.model.entity.News;
import com.example.kanbamtable.model.entity.Notification;
import com.example.kanbamtable.model.entity.Project;
import com.example.kanbamtable.model.entity.UserAccount;
import com.example.kanbamtable.repository.NewsJPARepository;
import com.example.kanbamtable.repository.NotificationJPARepository;
import com.example.kanbamtable.repository.UserAccountJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserAccountJPARepository ur;

    @Autowired
    NotificationJPARepository nr;

    @Autowired
    NewsJPARepository nr2;


    public void createUser(UserAccount user){
        ur.save(user);
    }

    public List<UserAccount> findByEmail(String email, int page){

        Pageable pageable = PageRequest.of(page, 10);

        return ur.findAllByEmailIsLike(email,pageable);

    }

    public List<Notification> getUsersNotifications(int page){
        UserAccount user=  ur.findByEmail( SecurityContextHolder.getContext().getAuthentication().getName()).get();
        Pageable pageable = PageRequest.of(page, 10);
        return nr.findAllByUserAccountOOrderByIdDesc(user,pageable);

    }

    public List<Project> getUsersOwnedProjects(int page){
        UserAccount user=  ur.findByEmail( SecurityContextHolder.getContext().getAuthentication().getName()).get();
        List<Project> ownedProjects = user.getOwnedProjects();

        return  ownedProjects.size()>page*10?
                    ownedProjects.size()>page*10+10?
                            ownedProjects.subList(page*10,page*10+10):
                            ownedProjects.subList(page*10,ownedProjects.size()):
                            new ArrayList<Project>();

    }


    public List<Project> getUsersJoinedProjects(int page){
        UserAccount user=  ur.findByEmail( SecurityContextHolder.getContext().getAuthentication().getName()).get();
        List<Project> joinedProjects = user.getJoinedProjects();

        return  joinedProjects.size()>page*10?
                joinedProjects.size()>page*10+10?
                        joinedProjects.subList(page*10,page*10+10):
                        joinedProjects.subList(page*10,joinedProjects.size()):
                new ArrayList<Project>();

    }



    public List<News> getNews(int page){
        //Pageable pageable = PageRequest.of(page, 10);

        return nr2.findAll();

    }






}
