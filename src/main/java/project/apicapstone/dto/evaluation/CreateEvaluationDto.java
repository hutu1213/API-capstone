package project.apicapstone.dto.evaluation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import project.apicapstone.common.util.DateUtils;
import project.apicapstone.validation.annonation.UniqueEvaluationId;

import javax.persistence.Column;
import java.time.LocalDate;


@Data
public class CreateEvaluationDto {
    @UniqueEvaluationId
    private String evaluationId;

    private String content;

    private String type;

    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate createDate;
    private String createTime;


    private int rating;

    private String employeeId;

    private String applicantId;

}
