package project.apicapstone.dto.account;

import lombok.Data;

@Data
public class ChangePasswordDto {
    private String username;
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
}
