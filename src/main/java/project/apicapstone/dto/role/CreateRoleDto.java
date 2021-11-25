package project.apicapstone.dto.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.apicapstone.validation.annonation.UniqueRoleId;

import javax.persistence.Column;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleDto {
    @Column
    @UniqueRoleId
    private String roleId;
    @Size(min = 3, max = 25, message = "{role.name.size}")
    private String roleName;
    @Column
    private String roleDescription;
}
