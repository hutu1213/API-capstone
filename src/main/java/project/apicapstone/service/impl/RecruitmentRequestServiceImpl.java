package project.apicapstone.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.recruitmentRequest.CreateRecruitmentRequestDto;
import project.apicapstone.dto.recruitmentRequest.PagingFormatRecruitmentRequestDto;
import project.apicapstone.dto.recruitmentRequest.UpdateRecruitmentRequestDto;
import project.apicapstone.entity.RecruitmentRequest;
import project.apicapstone.repository.EmployeeRepository;
import project.apicapstone.repository.RecruitmentRequestRepository;
import project.apicapstone.repository.TitleRepository;
import project.apicapstone.service.RecruitmentRequestService;

import java.time.LocalDate;
import java.util.List;

@Service
public class RecruitmentRequestServiceImpl implements RecruitmentRequestService {
    private final RecruitmentRequestRepository recruitmentRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final TitleRepository titleRepository;

    public RecruitmentRequestServiceImpl(RecruitmentRequestRepository recruitmentRequestRepository, EmployeeRepository employeeRepository, TitleRepository titleRepository) {
        this.recruitmentRequestRepository = recruitmentRequestRepository;
        this.employeeRepository = employeeRepository;
        this.titleRepository = titleRepository;
    }

    @Override
    public boolean isExisted(Long s) {
        return recruitmentRequestRepository.existsById(s);
    }

    @Override
    public Page<RecruitmentRequest> findAllRecruitmentRequest(Pageable pageable) {
        return recruitmentRequestRepository.findAllRecruitmentRequest(pageable);
    }

    @Override
    public Object pagingFormat(Page<RecruitmentRequest> recruitmentRequestPage) {
        PagingFormatRecruitmentRequestDto dto = new PagingFormatRecruitmentRequestDto();
        dto.setPageSize(recruitmentRequestPage.getSize());
        dto.setTotalRecordCount(recruitmentRequestPage.getTotalElements());
        dto.setPageNumber(recruitmentRequestPage.getNumber());
        dto.setRecords(recruitmentRequestPage.toList());
        return dto;
    }

    @Override
    public List<RecruitmentRequest> findAll() {
        return recruitmentRequestRepository.findAll();
    }

    @Override
    public RecruitmentRequest createRecruitmentRequest(CreateRecruitmentRequestDto dto) {
        RecruitmentRequest recruitmentRequest = new RecruitmentRequest();
        recruitmentRequest.setVacancies(dto.getVacancies());
        recruitmentRequest.setReasonRecruitment(dto.getReasonRecruitment());
        recruitmentRequest.setCreateDate(LocalDate.now());
        recruitmentRequest.setStatus(dto.getStatus());
        recruitmentRequest.setDescriptionWork(dto.getDescriptionWork());
        recruitmentRequest.setEmployee(employeeRepository.getById(dto.getEmployeeId()));
        recruitmentRequest.setTitle(titleRepository.getById(dto.getTitleId()));
        return recruitmentRequestRepository.save(recruitmentRequest);
    }

    @Override
    public RecruitmentRequest findRecruitmentRequestById(Long id) {
        return recruitmentRequestRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        recruitmentRequestRepository.deleteById(id);
    }

    @Override
    public void updateRecruitmentRequest(UpdateRecruitmentRequestDto dto, Long recruitmentRequestId) {
        RecruitmentRequest recruitmentRequest = recruitmentRequestRepository.getById(recruitmentRequestId);
        recruitmentRequest.setVacancies(dto.getVacancies());
        recruitmentRequest.setReasonRecruitment(dto.getReasonRecruitment());
        recruitmentRequest.setCreateDate(LocalDate.now());
        recruitmentRequest.setStatus(dto.getStatus());
        recruitmentRequest.setDescriptionWork(dto.getDescriptionWork());
        recruitmentRequest.setEmployee(employeeRepository.getById(dto.getEmployeeId()));
        recruitmentRequest.setTitle(titleRepository.getById(dto.getTitleId()));
        recruitmentRequestRepository.save(recruitmentRequest);
    }

    @Override
    public Page<RecruitmentRequest> findRecruitmentRequestByEmployeeId(String id, Pageable pageable) {
        return recruitmentRequestRepository.getByEmployeeId(id, pageable);
    }

    @Override
    public Page<RecruitmentRequest> findRecruitmentRequestByStatus(String status, Pageable pageable) {
        return recruitmentRequestRepository.getAllByStatus(status, pageable);
    }

    @Override
    public List<RecruitmentRequest> getByStatus(String status) {
        return recruitmentRequestRepository.getRecruitmentRequestsByStatus(status);
    }

    @Override
    public RecruitmentRequest searchById(Long paramSearch) {
        return recruitmentRequestRepository.findByRecruitmentRequestId(paramSearch);
    }
}
