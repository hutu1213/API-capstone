package project.apicapstone.dto.evaluation;

import lombok.Data;
import project.apicapstone.validation.annonation.UniqueEvaluationId;



@Data
public class CreateEvaluationDto {
    @UniqueEvaluationId
    private String evaluationId;

    private String content;

    private String type;

    private String employeeId;

    private String applicantId;

}
