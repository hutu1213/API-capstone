package project.apicapstone.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.apicapstone.entity.Employee;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagingFormatEmployeeDto {
    private int pageNumber;

    private int pageSize;

    private Long totalRecordCount;

    private List<Employee> records;

}
