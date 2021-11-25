package project.apicapstone.dto.applicant;

import lombok.Data;


@Data
public class CreateApplicantDto {

    private String applicantId;

    private String applicantName;

    private String dateBirth;

    private String address;

    private String phone;

    private String gender;

    private String email;

    private String certification;

    private String status;

    private String resumeFile;
}
