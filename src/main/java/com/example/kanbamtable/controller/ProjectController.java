package com.example.kanbamtable.controller;


import com.example.kanbamtable.model.entity.Project;
import com.example.kanbamtable.model.entity.Task;
import com.example.kanbamtable.model.entity.UserAccount;
import com.example.kanbamtable.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/projects")
public class ProjectController {


    @Autowired
    ProjectService ps;

    @PostMapping("/create")
    public void createProject (@RequestParam(name = "name")String name){
        ps.createProject(name);
    }


    @DeleteMapping("/delete")
    public void deleteProject(@RequestParam(name = "projectId")int projectId) throws Exception {
        ps.deleteProject(projectId);
    }

    @GetMapping("/tasks")
    public List<Task> getProjectTask(@RequestParam(name = "projectId")int projectId) throws Exception {
        return ps.getProjectTask(projectId);
    }

    @GetMapping("/workers")
    public List<UserAccount> getProjectWorkers(@RequestParam(name = "projectId")int projectId) throws Exception {
       return ps.getProjectWorkers(projectId);
    }
    @PostMapping("/join/request")
    public void sendProjectJoinNotification(
            @RequestParam(name = "email")String email,
            @RequestParam(name = "projectId")int projectId) throws Exception {

        ps.sendProjectJoinNotification(email,projectId);

    }


    @PostMapping("/join/accept")
    public void joinUserToProject(@RequestParam(name = "projectId") int projectId) throws Exception {
        ps.joinUserToProject(projectId);
    }

    @PostMapping("/separate")
    public void separateUserFromProject(
            @RequestParam(name = "userEmail")String userEmail,
            @RequestParam(name = "projectId")int projectId) throws Exception {

       ps.separateUserFromProject(userEmail,projectId);
    }


}
