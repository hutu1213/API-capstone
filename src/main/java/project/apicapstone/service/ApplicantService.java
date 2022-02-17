package project.apicapstone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import project.apicapstone.dto.applicant.CreateApplicantDto;
import project.apicapstone.dto.applicant.ProcessApplicantDto;
import project.apicapstone.dto.applicant.UpdateApplicantDto;
import project.apicapstone.entity.Applicant;

import java.util.List;

public interface ApplicantService {
    List<Applicant> findAll();

    Applicant createApplicant(CreateApplicantDto dto);

    boolean isExisted(String s);

    Page<Applicant> findAllApplicant(Pageable pageable);

    Object pagingFormat(Page<Applicant> applicantPage);

    Applicant findApplicantById(String id);

    List<Applicant> findApplicantByNameOrId(String paramSearch);

    void updateApplicant(UpdateApplicantDto dto, String applicantId);

    void deleteById(String id);

    Page<Applicant> search(String paramSearch, Pageable pageable);

    @Transactional //anotation to access Lob field
    List<ProcessApplicantDto> getAllProcessApplicantDtoByJobPosting(String jobPostingId);

    int updateScanData(String scanData, String id);

    int updateScore(Double score, String id);

    List<Applicant> getAllByStatus(String status);

}
