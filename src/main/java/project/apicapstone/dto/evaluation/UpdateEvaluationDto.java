package project.apicapstone.dto.evaluation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import project.apicapstone.common.util.DateUtils;

import java.time.LocalDate;

@Data
public class UpdateEvaluationDto {
    private String evaluationId;
    private String content;
    private String type;

    private Boolean result;
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate updateDate;
    private String createTime;
    private int rating;
    private String employeeId;
    private String applicantId;
}
