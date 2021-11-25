package project.apicapstone.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import project.apicapstone.entity.Employee;
import project.apicapstone.validation.annonation.ConfirmPassword;
import project.apicapstone.validation.annonation.UniqueUsername;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfirmPassword
public class CreateAccountDto {

    private String accountId;
    @UniqueUsername
    private String username;
    @NotBlank(message = "{account.password.not-blank}")
    private String password;
    private String confirmPassword;
    private String status;
    //@FindEmployeeId
    private String employeeId;

}
