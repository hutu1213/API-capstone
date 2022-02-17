package project.apicapstone.dto.title;

import lombok.Data;
import org.hibernate.annotations.Check;
import project.apicapstone.validation.annonation.*;

import javax.validation.constraints.NotBlank;

@Data
@CheckUniquePositionAndDepartment
public class CreateTitleDto {
    @UniqueTitleId
    private String titleId;
    //@NotBlank(message = "{title.jobTitle.not-blank}")
    private String jobTitle;
    @FindPositionId
    private String positionId;
    @FindDepartmentId
    private String departmentId;

}
