package project.apicapstone.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import project.apicapstone.entity.Employee;

import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountDto {
//    private String employeeId;
    private String accountId;
    private String username;
    private String password;
    private String confirmPassword;
    private String status;


}
