package project.apicapstone.service.impl;

import org.springframework.stereotype.Service;
import project.apicapstone.repository.TaskRepository;
import project.apicapstone.service.TaskService;
@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }
}
