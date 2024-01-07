package com.example.kanbamtable.service;

import com.example.kanbamtable.dto.TaskDTO;
import com.example.kanbamtable.model.entity.Project;
import com.example.kanbamtable.model.entity.Task;
import com.example.kanbamtable.model.entity.UserAccount;
import com.example.kanbamtable.repository.ProjectJPARepository;
import com.example.kanbamtable.repository.TaskJPARepository;
import com.example.kanbamtable.repository.UserAccountJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    UserAccountJPARepository ur;

    @Autowired
    TaskJPARepository tr;

    @Autowired
    ProjectJPARepository pr;


    public void createTask(TaskDTO newTaskDTO) throws Exception {

        UserAccount owner=  ur.findByEmail( SecurityContextHolder.getContext().getAuthentication().getName()).get();
        Optional<Project> project= pr.findByIdAndOwner(newTaskDTO.getProjectId(),owner);

        if(project.isPresent()) {

            Task newTask = new Task();
            newTask.setResume(newTaskDTO.getResume());
            newTask.setDescription(newTaskDTO.getDescription());
            newTask.setProject(project.get());
            newTask.setState("Undone");

            tr.save(newTask);
        }
        else
            throw new Exception("User doesn't own the project");


    }

    public void changeTaskStateToUndone(int taskId){

        UserAccount user=  ur.findByEmail( SecurityContextHolder.getContext().getAuthentication().getName()).get();
        Task task = tr.findById(taskId).get();

        if(user.isOwnerOfProject(task.getProject().getId()) || user.isWorkerOfProject(task.getProject().getId())){
            task.setState("Undone");
            task.setUser(null);
        }

        tr.save(task);

    }

    public void changeTaskStateToProcessing(int taskId){

        UserAccount user=  ur.findByEmail( SecurityContextHolder.getContext().getAuthentication().getName()).get();
        Task task = tr.findById(taskId).get();

        if(user.isOwnerOfProject(task.getProject().getId()) || user.isWorkerOfProject(task.getProject().getId())) {
            if(task.getState().equals("Undone")){
                task.setUser(user);
            }
            task.setState("Processing");
        }

        tr.save(task);

    }

    public void changeTaskStateToDone(int taskId){

        UserAccount user=  ur.findByEmail( SecurityContextHolder.getContext().getAuthentication().getName()).get();
        Task task = tr.findById(taskId).get();

        if(user.isOwnerOfProject(task.getProject().getId()) || user.isWorkerOfProject(task.getProject().getId())) {
            if(task.getState().equals("Undone")){
                task.setUser(user);
            }
            task.setState("Done");
        }

        tr.save(task);
    }


}
