package project.apicapstone.dto.workplace;

import lombok.Data;

import project.apicapstone.entity.Workplace;

import java.util.List;
@Data
public class PagingFormatWorkPlaceDto {
    private int pageNumber;

    private int pageSize;

    private Long totalRecordCount;

    private List<Workplace> records;
}
