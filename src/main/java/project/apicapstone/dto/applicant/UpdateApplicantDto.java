package project.apicapstone.dto.applicant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import project.apicapstone.common.util.DateUtils;

import project.apicapstone.validation.annonation.FindJobPostingId;


import javax.persistence.Column;
import javax.validation.constraints.Email;
import java.time.LocalDate;
@Data
public class UpdateApplicantDto {

    private String applicantId;

    //@NotBlank(message = "{applicant.name.not-blank}")
    private String applicantName;

    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate dateBirth;

    //@NotBlank(message = "{applicant.address.not-blank}")
    private String address;

    //@CheckPhoneNumber
    private String phone;

    //@NotBlank(message = "{applicant.gender.not-blank}")
    private String gender;

    @Email
    private String email;

    private String certification;

    // @NotBlank(message = "{applicant.status.not-blank}")
    private String status;

    private String resumeFile;
   // private String scanData;

    private double evaluateScore;
    @DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
    private LocalDate interviewDate;

    private String interviewTime;
    private String stage;
    @FindJobPostingId
    private String jobPostingId;
}
