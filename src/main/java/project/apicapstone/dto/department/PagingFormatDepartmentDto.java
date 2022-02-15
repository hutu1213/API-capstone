package project.apicapstone.dto.department;


import lombok.Data;

import project.apicapstone.entity.Department;


import java.util.List;
@Data

public class PagingFormatDepartmentDto {
    private int pageNumber;

    private int pageSize;

    private Long totalRecordCount;

    private List<Department> records;
}
