package project.apicapstone.dto.department;

import project.apicapstone.validation.annonation.CheckDepartmentId;
import project.apicapstone.validation.annonation.UniqueDepartmentId;
import project.apicapstone.validation.annonation.UniqueEmployeeId;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class CreateDepartmentDto {

    @CheckDepartmentId
    @UniqueDepartmentId
    private String departmentId;

    @NotBlank(message = "{department.name.not-blank}")
    private String departmentName;

    public CreateDepartmentDto() {
    }

    public CreateDepartmentDto(String departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
