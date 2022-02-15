package project.apicapstone.dto.criteria;

import lombok.Data;

import project.apicapstone.entity.Criteria;

import java.util.List;

@Data
public class PagingFormatCriteriaDto {
    private int pageNumber;

    private int pageSize;

    private Long totalRecordCount;

    private List<Criteria> records;
}
