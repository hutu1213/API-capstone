package project.apicapstone.dto.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddRoleDto {
    @NotNull
    private String accountId;

    @NotNull
    private String roleId;
}
