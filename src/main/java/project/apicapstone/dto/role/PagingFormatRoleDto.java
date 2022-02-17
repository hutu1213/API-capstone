package project.apicapstone.dto.role;

import lombok.Data;
import project.apicapstone.entity.Role;

import java.util.List;

@Data
public class PagingFormatRoleDto {
    private int pageNumber;

    private int pageSize;

    private Long totalRecordCount;

    private List<Role> records;
}
