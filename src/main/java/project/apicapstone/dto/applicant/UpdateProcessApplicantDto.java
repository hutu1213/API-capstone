package project.apicapstone.dto.applicant;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateProcessApplicantDto implements Serializable {
    private final String applicantId;
    private final String scanData;
    private final double score;
}
