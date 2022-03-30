package project.apicapstone.dto.recruitmentRequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import project.apicapstone.common.util.DateUtils;
import project.apicapstone.validation.annonation.UniqueRecruitmentRequestId;


@Data
public class CreateRecruitmentRequestDto {
//    @UniqueRecruitmentRequestId
//    private String recruitmentRequestId;

    private String vacancies;

    private String reasonRecruitment;


    private String status;

    private String descriptionWork;

    private String employeeId;

    private String titleId;
}
