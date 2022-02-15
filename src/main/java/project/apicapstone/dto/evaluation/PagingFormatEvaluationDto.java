package project.apicapstone.dto.evaluation;

import lombok.Data;
import project.apicapstone.entity.Evaluation;


import java.util.List;

@Data
public class PagingFormatEvaluationDto {
    private int pageNumber;

    private int pageSize;

    private Long totalRecordCount;

    private List<Evaluation> records;
}
