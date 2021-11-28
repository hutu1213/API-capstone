package project.apicapstone.dto.title;

import lombok.Data;
import project.apicapstone.validation.annonation.UniqueTitleId;

import javax.validation.constraints.NotBlank;


@Data
public class CreateTitleDto {
    @UniqueTitleId
    private String titleId;
    @NotBlank(message = "{title.jobTitle.not-blank}")
    private String jobTitle;
}
