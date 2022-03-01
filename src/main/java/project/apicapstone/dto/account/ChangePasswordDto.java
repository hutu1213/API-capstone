package project.apicapstone.dto.account;

import lombok.Data;
import project.apicapstone.validation.annonation.FindUsername;

@Data
public class ChangePasswordDto {
    @FindUsername
    private String username;
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
}
