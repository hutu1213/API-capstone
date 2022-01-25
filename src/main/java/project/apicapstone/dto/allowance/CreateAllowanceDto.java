package project.apicapstone.dto.allowance;

import lombok.Data;
import project.apicapstone.validation.annonation.FindContractId;
import project.apicapstone.validation.annonation.UniqueAllowanceId;

import javax.validation.constraints.NotBlank;


@Data
public class CreateAllowanceDto {
    @UniqueAllowanceId
    private String allowanceId;

    private String allowanceName;

    private String type;

    private float amount;

    @FindContractId
    private String contractId;
}
