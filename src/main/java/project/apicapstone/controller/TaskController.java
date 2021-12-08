package project.apicapstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.apicapstone.service.TaskService;

@RestController
@RequestMapping(value = "/api/task")
public class TaskController {
    private TaskService taskService;
    public TaskController(TaskService taskService){
        this.taskService=taskService;
    }
}
