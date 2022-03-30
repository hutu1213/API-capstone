package project.apicapstone.dto.criteria;

import lombok.Data;

@Data
public class UpdateCriteriaDto {
    private Long criteriaId;

    private String criteriaDescription;
    private Double weight;
    private String keyword;
    private String jobPostingId;
}
