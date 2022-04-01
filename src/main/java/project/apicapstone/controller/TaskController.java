package project.apicapstone.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.task.AddEmployeeToTaskDto;
import project.apicapstone.dto.task.CreateTaskDto;
import project.apicapstone.dto.task.UpdateTaskDto;
import project.apicapstone.entity.Task;
import project.apicapstone.service.EmployeeService;
import project.apicapstone.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/api/task")
public class TaskController {
    private final TaskService taskService;
    private final EmployeeService employeeService;

    public TaskController(TaskService taskService, EmployeeService employeeService) {
        this.taskService = taskService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public Object findAllTask(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Task> taskPage = taskService.findAllTask(pageable);
        return ResponseHandler.getResponse(taskService.pagingFormat(taskPage), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public Object findAll() {
        List<Task> tasks = taskService.findAll();
        return ResponseHandler.getResponse(tasks, HttpStatus.OK);
    }

    @PostMapping("/create-task")
    public Object createTask(@Valid @RequestBody CreateTaskDto dto,
                             BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Task createTask = taskService.createTask(dto);

        return ResponseHandler.getResponse(createTask, HttpStatus.CREATED);
    }

    @DeleteMapping()
    public Object deleteTask(@RequestParam(name = "id") Long id) {
        taskService.deleteById(id);
        return ResponseHandler.getResponse(HttpStatus.OK);
    }

    @PutMapping()
    public Object updateTask(@Valid @RequestBody UpdateTaskDto dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }
        taskService.updateTask(dto, dto.getTaskId());
        return ResponseHandler.getResponse(HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public Object findTaskById(@PathVariable("id") Long id) {
        Task task = taskService.findTaskById(id);
        return ResponseHandler.getResponse(task, HttpStatus.OK);
    }

//    @GetMapping("/search/{paramSearch}")
//    public Object findTaskByNameOrId(@PathVariable String paramSearch) {
//        List<Task> taskList = taskService.findTaskByNameOrId(paramSearch);
//        if (taskList.isEmpty()) {
//            return ResponseHandler.getErrors("Not found", HttpStatus.NOT_FOUND);
//        }
//        return ResponseHandler.getResponse(taskList, HttpStatus.OK);
//    }
//
//    @GetMapping("/search-paging")
//    public Object search(@RequestParam(required = false) String name, @RequestParam(required = false) Long id,@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Task> taskPage = taskService.search(name,id, pageable);
//
//        return ResponseHandler.getResponse(taskService.pagingFormat(taskPage), HttpStatus.OK);
//    }

    @PostMapping("/add-employee")
    public Object addEmployeeToTask(@Valid @RequestBody AddEmployeeToTaskDto dto, BindingResult errors) {
//        if (errors.hasErrors())
//            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
//
//        for (int i = 0; i < dto.getEmployeeIdList().size(); i++) {
//            String employeeId = dto.getEmployeeIdList().get(i);
//            if (employeeService.findByTaskIdAndEmployeeId(dto.getTaskId(), employeeId)) {
//                return ResponseHandler.getErrors("Mã nhân viên " + employeeId + " đã tồn tại trong công việc", HttpStatus.BAD_REQUEST);
//            }
//        }
//        taskService.addEmployee(dto);
        return ResponseHandler.getResponse("Add Successful!", HttpStatus.OK);
    }


}
