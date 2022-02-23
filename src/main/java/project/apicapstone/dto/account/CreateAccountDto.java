package project.apicapstone.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.apicapstone.validation.annonation.*;

import javax.validation.constraints.NotBlank;

@Data
@ConfirmPassword
public class CreateAccountDto {
//    @UniqueAccountId
//    private String accountId;
    @UniqueUsername
    private String username;
//    @NotBlank(message = "{account.password.not-blank}")
    private String password;
    private String confirmPassword;
//    @NotBlank(message = "{account.status.not-blank}")
//    private String status;
    @FindEmployeeId
    @CheckEmployeeIdExistInAccount
    private String employeeId;

}
