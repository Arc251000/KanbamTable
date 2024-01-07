package com.example.kanbamtable.service;

import com.example.kanbamtable.model.entity.Notification;
import com.example.kanbamtable.model.entity.Project;
import com.example.kanbamtable.model.entity.Task;
import com.example.kanbamtable.model.entity.UserAccount;
import com.example.kanbamtable.repository.NotificationJPARepository;
import com.example.kanbamtable.repository.ProjectJPARepository;
import com.example.kanbamtable.repository.TaskJPARepository;
import com.example.kanbamtable.repository.UserAccountJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {


    @Autowired
    UserAccountJPARepository ur;

    @Autowired
    ProjectJPARepository pr;

    @Autowired
    NotificationJPARepository nr;

    @Autowired
    TaskJPARepository tr;

    public void createProject (String name){

        UserAccount user=  ur.findByEmail( SecurityContextHolder.getContext().getAuthentication().getName()).get();

        Project newProject = new Project();
        newProject.setName(name);
        newProject.setOwner(user);

        pr.save(newProject);

    }


    public void deleteProject(int id) throws Exception {

        UserAccount user=  ur.findByEmail( SecurityContextHolder.getContext().getAuthentication().getName()).get();

        Optional<Project> project= pr.findByIdAndOwner(id,user);

        if(project.isPresent())
            pr.delete(project.get());
        else
            throw new Exception("User doesn't own the project");

    }

    public List<Task> getProjectTask(int projectId) throws Exception {

        UserAccount user=  ur.findByEmail( SecurityContextHolder.getContext().getAuthentication().getName()).get();

        if(user.isOwnerOfProject(projectId)||user.isWorkerOfProject(projectId))
            return pr.findById(projectId).get().getTasks();
        else
            throw new Exception("User has no access to this information");
    }

    public void sendProjectJoinNotification(String email, int projectId) throws Exception {
        UserAccount owner=  ur.findByEmail( SecurityContextHolder.getContext().getAuthentication().getName()).get();
        Optional<Project> project= pr.findByIdAndOwner(projectId,owner);
        UserAccount user=ur.findByEmail(email).get();

        if(project.isPresent()) {
            Notification newNotification = new Notification();
            newNotification.setText(owner.getEmail()+" te ha invitado a unirte al proyecto: "+project.get().getName());
            newNotification.setUrl("");
            newNotification.setUserAccount(user);
            nr.save(newNotification);

        }
        else
            throw new Exception("User doesn't own the project");


    }


    public void joinUserToProject( int projectId) throws Exception {

        UserAccount user=  ur.findByEmail( SecurityContextHolder.getContext().getAuthentication().getName()).get();
        Optional<Notification> notification= nr.findByUserAccountAndUrl(user,"");

        if(notification.isPresent()) {
            user.getJoinedProjects().add(pr.findById(projectId).get());
            ur.save(user);
        }
        else
            throw new Exception("Owner doesn't invite user to the project");

    }










}
