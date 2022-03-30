package project.apicapstone.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.employee.UpdateEmployeeDto;
import project.apicapstone.dto.trainingCourse.AddEmployeeDto;
import project.apicapstone.dto.trainingCourse.CreateCourseDto;
import project.apicapstone.dto.trainingCourse.UpdateCourseDto;
import project.apicapstone.entity.TrainingCourse;
import project.apicapstone.service.EmployeeService;
import project.apicapstone.service.TrainingCourseService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/api/training-course")
public class TrainingCourseController {
    private TrainingCourseService trainingCourseService;
    private EmployeeService employeeService;

    public TrainingCourseController(TrainingCourseService trainingCourseService, EmployeeService employeeService) {
        this.trainingCourseService = trainingCourseService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public Object findAllCourse(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TrainingCourse> trainingCoursePage = trainingCourseService.findAllCourse(pageable);
        return ResponseHandler.getResponse(trainingCourseService.pagingFormat(trainingCoursePage), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public Object findAll() {
        List<TrainingCourse> trainingCourses = trainingCourseService.findAll();
        return ResponseHandler.getResponse(trainingCourses, HttpStatus.OK);
    }

    @PostMapping("/create-course")
    public Object createCourse(@Valid @RequestBody CreateCourseDto dto,
                               BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        TrainingCourse trainingCourses = trainingCourseService.createCourse(dto);

        return ResponseHandler.getResponse(trainingCourses, HttpStatus.CREATED);
    }

    @PostMapping("/add-employee")
    public Object addEmployeeToCourse(@Valid @RequestBody AddEmployeeDto dto, BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        for (int i = 0; i < dto.getEmployeeIdList().size(); i++) {
            String employeeId = dto.getEmployeeIdList().get(i);
            if (employeeService.findByCourseIdAndEmployeeId(dto.getCourseId(), employeeId)) {
                return ResponseHandler.getErrors("Mã nhân viên " + employeeId + " đã tồn tại trong khóa học", HttpStatus.BAD_REQUEST);
            }
        }
        trainingCourseService.addEmployee(dto);
        return ResponseHandler.getResponse("Add Successful!", HttpStatus.OK);
    }
    @DeleteMapping()
    public Object deleteCourse(@RequestParam(name = "id") String id) {
        trainingCourseService.deleteById(id);
        return ResponseHandler.getResponse(HttpStatus.OK);
    }

    @PutMapping()
    public Object updateCourse(@Valid @RequestBody UpdateCourseDto dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }
        trainingCourseService.updateCourse(dto, dto.getCourseId());
        return ResponseHandler.getResponse(HttpStatus.OK);
    }
}
