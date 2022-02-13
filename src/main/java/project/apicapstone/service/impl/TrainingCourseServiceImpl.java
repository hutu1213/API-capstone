package project.apicapstone.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.trainingCourse.AddEmployeeDto;
import project.apicapstone.dto.trainingCourse.CreateCourseDto;
import project.apicapstone.dto.trainingCourse.PagingFormatCourseDto;
import project.apicapstone.dto.trainingCourse.UpdateCourseDto;
import project.apicapstone.entity.Employee;
import project.apicapstone.entity.TrainingCourse;
import project.apicapstone.repository.EmployeeRepository;
import project.apicapstone.repository.TrainingCourseRepository;
import project.apicapstone.service.TrainingCourseService;

import java.util.List;

@Service
public class TrainingCourseServiceImpl implements TrainingCourseService {
    private TrainingCourseRepository trainingCourseRepository;
    private EmployeeRepository employeeRepository;

    public TrainingCourseServiceImpl(TrainingCourseRepository trainingCourseRepository, EmployeeRepository employeeRepository) {
        this.trainingCourseRepository = trainingCourseRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean isExisted(String s) {
        return trainingCourseRepository.existsById(s);
    }

    @Override
    public Page<TrainingCourse> findAllCourse(Pageable pageable) {
        return trainingCourseRepository.findAllCourse(pageable);
    }

    @Override
    public Object pagingFormat(Page<TrainingCourse> trainingCoursePage) {
        PagingFormatCourseDto dto = new PagingFormatCourseDto();
        dto.setPageSize(trainingCoursePage.getSize());
        dto.setTotalRecordCount(trainingCoursePage.getTotalElements());
        dto.setPageNumber(trainingCoursePage.getNumber());
        dto.setRecords(trainingCoursePage.toList());
        return dto;
    }

    @Override
    public List<TrainingCourse> findAll() {
        return trainingCourseRepository.findAll();
    }

    @Override
    public TrainingCourse createCourse(CreateCourseDto dto) {
        TrainingCourse trainingCourse = new TrainingCourse();
        trainingCourse.setCourseId(dto.getCourseId());
        trainingCourse.setCourseName(dto.getCourseName());
        trainingCourse.setTrainer(dto.getTrainer());
        trainingCourse.setStartDate(dto.getStartDate());
        trainingCourse.setEndDate(dto.getEndDate());
        trainingCourse.setDescription(dto.getDescription());
        return trainingCourseRepository.save(trainingCourse);
    }

    @Override
    public void addEmployee(AddEmployeeDto dto) {

        TrainingCourse trainingCourse = trainingCourseRepository.getById(dto.getCourseId());

        for (int i = 0; i < dto.getEmployeeIdList().size(); i++) {
            Employee employee = employeeRepository.getById(dto.getEmployeeIdList().get(i));
            trainingCourse.addEmployee(employee);

        }
        trainingCourseRepository.save(trainingCourse);

    }

    @Override
    public void deleteById(String id) {
        trainingCourseRepository.deleteById(id);
    }

    @Override
    public void updateCourse(UpdateCourseDto dto, String courseId) {
        TrainingCourse trainingCourse = trainingCourseRepository.getById(courseId);
        trainingCourse.setCourseName(dto.getCourseName());
        trainingCourse.setTrainer(dto.getTrainer());
        trainingCourse.setStartDate(dto.getStartDate());
        trainingCourse.setEndDate(dto.getEndDate());
        trainingCourse.setDescription(dto.getDescription());
        trainingCourseRepository.save(trainingCourse);
    }
}
