package project.apicapstone.dto.subarea;

import lombok.Data;
import project.apicapstone.entity.Subarea;


import java.util.List;

@Data
public class PagingFormatSubareaDto {
    private int pageNumber;

    private int pageSize;

    private Long totalRecordCount;

    private List<Subarea> records;
}
