package project.apicapstone.dto.jobPosting;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import project.apicapstone.common.util.DateUtils;
import project.apicapstone.validation.annonation.FindTitleId;

import java.time.LocalDate;

@Data
public class UpdateJobPostingDto {
    private String jobPostingId;

    private String postTitle;

    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate datePost;

    private String jobDescription;

    private String jobRequirements;

    private String status;

    private String benefit;

    @FindTitleId
    private String titleId;
}
