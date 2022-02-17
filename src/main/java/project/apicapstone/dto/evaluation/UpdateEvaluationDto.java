package project.apicapstone.dto.evaluation;

import lombok.Data;

@Data
public class UpdateEvaluationDto {
    private String evaluationId;
    private String content;
    private String type;
    private String employeeId;
    private String applicantId;
}
