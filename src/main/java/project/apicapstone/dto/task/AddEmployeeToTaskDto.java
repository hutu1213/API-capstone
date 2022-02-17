package project.apicapstone.dto.task;

import lombok.Data;

import project.apicapstone.validation.annonation.FindTaskId;

import java.util.List;
@Data
public class AddEmployeeToTaskDto {
    @FindTaskId
    private String taskId;
    private List<String> employeeIdList;
}
