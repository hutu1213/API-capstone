package project.apicapstone.dto.applicant;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApplicantWithScoreDto implements Serializable {
    @JsonProperty("applicantId")
    private String applicantId;
    @JsonProperty("score")
    private double score;
}
