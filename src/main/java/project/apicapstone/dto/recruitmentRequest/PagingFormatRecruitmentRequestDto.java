package project.apicapstone.dto.recruitmentRequest;

import lombok.Data;

import project.apicapstone.entity.RecruitmentRequest;

import java.util.List;
@Data
public class PagingFormatRecruitmentRequestDto {
    private int pageNumber;

    private int pageSize;

    private Long totalRecordCount;

    private List<RecruitmentRequest> records;
}
