package project.apicapstone.dto.account;

import lombok.Data;
import project.apicapstone.validation.annonation.FindRoleId;

@Data
public class UpdateAccountDto {
    private String accountId;
    private String status;
    @FindRoleId
    private String roleId;
}
