package com.example.kanbamtable.controller;


import com.example.kanbamtable.model.entity.News;
import com.example.kanbamtable.model.entity.Notification;
import com.example.kanbamtable.model.entity.Project;
import com.example.kanbamtable.model.entity.UserAccount;
import com.example.kanbamtable.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService us;

    @GetMapping("/search")
    public List<UserAccount> findByEmail(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "page")int page){

        return us.findByEmail(email,page);
    }

    @GetMapping("/notifications")
    public List<Notification> getUsersNotifications(@RequestParam(name = "page")int page){
        return us.getUsersNotifications(page);
    }

    @GetMapping("/projects/owned")
    public List<Project> getUsersOwnedProjects(@RequestParam(name = "page")int page){
        return us.getUsersOwnedProjects(page);
    }


    @GetMapping("/projects/joined")
    public List<Project> getUsersJoinedProjects(@RequestParam(name = "page")int page){
        return us.getUsersJoinedProjects(page);
    }


    @GetMapping("/news")
    public List<News> getNews(@RequestParam(name = "page")int page){

        return us.getNews(page);

    }





}
