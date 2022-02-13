package project.apicapstone.dto.trainingCourse;

import lombok.Data;
import project.apicapstone.entity.Position;
import project.apicapstone.entity.TrainingCourse;

import java.util.List;
@Data
public class PagingFormatCourseDto {
    private int pageNumber;

    private int pageSize;

    private Long totalRecordCount;

    private List<TrainingCourse> records;
}
