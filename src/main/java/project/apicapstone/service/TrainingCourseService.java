package project.apicapstone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.apicapstone.dto.trainingCourse.AddEmployeeDto;
import project.apicapstone.dto.trainingCourse.CreateCourseDto;
import project.apicapstone.dto.trainingCourse.UpdateCourseDto;
import project.apicapstone.entity.TrainingCourse;

import java.util.List;

public interface TrainingCourseService {
    boolean isExisted(String s);

    Page<TrainingCourse> findAllCourse(Pageable pageable);

    Object pagingFormat(Page<TrainingCourse> trainingCoursePage);

    List<TrainingCourse> findAll();

    TrainingCourse createCourse(CreateCourseDto dto);

    void addEmployee(AddEmployeeDto dto);

    void deleteById(String id);

    void updateCourse(UpdateCourseDto dto, String courseId);
}
