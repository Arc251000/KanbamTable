package com.example.kanbamtable.controller;

import com.example.kanbamtable.dto.TaskDTO;
import com.example.kanbamtable.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/task")
public class TaskController {


    @Autowired
    TaskService ts;

    @PostMapping("/create")
    public void createTask(@RequestBody TaskDTO newTaskDTO) throws Exception {
       ts.createTask(newTaskDTO);
    }

    @DeleteMapping("/delete")
    public void deleteProject(@RequestParam(name = "taskId")int taskId) throws Exception {
        ts.deleteTask(taskId);
    }

    @PostMapping("/state/undone")
    public void changeTaskStateToUndone(@RequestParam(name="taskId") int taskId){
        ts.changeTaskStateToUndone(taskId);
    }

    @PostMapping("/state/processing")
    public void changeTaskStateToProcessing(@RequestParam(name="taskId")int taskId){
      ts.changeTaskStateToProcessing(taskId);
    }

    @PostMapping("/state/testing")
    public void changeTaskStateToTesting(@RequestParam(name="taskId")int taskId){
        ts.changeTaskStateToTesting(taskId);
    }

    @PostMapping("/state/done")
    public void changeTaskStateToDone(@RequestParam(name="taskId")int taskId){
        ts.changeTaskStateToDone(taskId);
    }




}
