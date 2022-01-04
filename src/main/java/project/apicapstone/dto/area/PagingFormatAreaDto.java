package project.apicapstone.dto.area;

import lombok.Data;
import project.apicapstone.entity.Area;
import project.apicapstone.entity.Workplace;

import java.util.List;
@Data
public class PagingFormatAreaDto {
    private int pageNumber;

    private int pageSize;

    private Long totalRecordCount;

    private List<Area> records;
}
