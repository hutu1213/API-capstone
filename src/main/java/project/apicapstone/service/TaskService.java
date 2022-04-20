package project.apicapstone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import project.apicapstone.dto.task.AddEmployeeToTaskDto;
import project.apicapstone.dto.task.CreateTaskDto;
import project.apicapstone.dto.task.UpdateTaskDto;
import project.apicapstone.entity.Task;

import java.util.List;

public interface TaskService {
    Page<Task> findAllTask(Pageable pageable);

    List<Task> findAll();

    void deleteById(Long id);

    Task findTaskById(Long id);

    List<Task> findTaskByNameOrId(String paramSearch);

    Object pagingFormat(Page<Task> taskPage);

    Task createTask(CreateTaskDto dto);

    void updateTask(UpdateTaskDto dto, Long taskId);

    boolean isExisted(Long s);



    void addEmployee(AddEmployeeToTaskDto dto);

    Page<Task> search(String name, Long id, Pageable pageable);

    void removeEmployee(AddEmployeeToTaskDto dto);
}
