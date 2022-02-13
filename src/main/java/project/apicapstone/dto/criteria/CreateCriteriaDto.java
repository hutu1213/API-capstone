package project.apicapstone.dto.criteria;

import lombok.Data;
import project.apicapstone.validation.annonation.UniqueCriteriaId;


@Data
public class CreateCriteriaDto {
    @UniqueCriteriaId
    private String criteriaId;

    private String criteriaDescription;

    private String weight;

    private String jobPostingId;
}
