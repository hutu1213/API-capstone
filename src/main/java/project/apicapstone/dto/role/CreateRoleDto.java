package project.apicapstone.dto.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleDto {
    @Column
    private String roleId;
    @Column
    @NotBlank
    private String roleName;
    @Column
    private String roleDescription;
}
