package project.apicapstone.dto.recruitmentRequest;

import lombok.Data;

import project.apicapstone.validation.annonation.CheckEmployeeIdInOrtherContractWithStatus;
import project.apicapstone.validation.annonation.CheckEmployeeIdInOrtherRecruitmentWithStatus;


@Data
public class CreateRecruitmentRequestDto {


    private String vacancies;

    private String reasonRecruitment;

    private String status;

    private String descriptionWork;
    @CheckEmployeeIdInOrtherRecruitmentWithStatus
    private String employeeId;

    private String titleId;
}
