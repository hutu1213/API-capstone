package project.apicapstone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.apicapstone.dto.recruitmentRequest.CreateRecruitmentRequestDto;
import project.apicapstone.dto.recruitmentRequest.UpdateRecruitmentRequestDto;
import project.apicapstone.entity.RecruitmentRequest;

import java.util.List;

public interface RecruitmentRequestService {
    boolean isExisted(String s);

    Page<RecruitmentRequest> findAllRecruitmentRequest(Pageable pageable);

    Object pagingFormat(Page<RecruitmentRequest> recruitmentRequestPage);

    List<RecruitmentRequest> findAll();

    RecruitmentRequest createRecruitmentRequest(CreateRecruitmentRequestDto dto);

    RecruitmentRequest findRecruitmentRequestById(String id);

    void deleteById(String id);

    void updateRecruitmentRequest(UpdateRecruitmentRequestDto dto, String recruitmentRequestId);

    Page<RecruitmentRequest> findRecruitmentRequestByEmployeeId(String id, Pageable pageable);

    Page<RecruitmentRequest> findRecruitmentRequestByStatus(String status, Pageable pageable);
}
