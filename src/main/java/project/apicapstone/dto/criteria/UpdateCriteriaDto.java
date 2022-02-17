package project.apicapstone.dto.criteria;

import lombok.Data;

@Data
public class UpdateCriteriaDto {
    private String criteriaId;

    private String criteriaDescription;

    private String weight;
    private String jobPostingId;
}
