package project.apicapstone.dto.trainingCourse;

import lombok.Data;
import project.apicapstone.validation.annonation.FindCourseId;


import java.util.List;

@Data
public class AddEmployeeDto {
    @FindCourseId
    private String courseId;
    private List<String> employeeIdList;
}
