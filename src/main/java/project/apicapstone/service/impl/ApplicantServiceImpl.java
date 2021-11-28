package project.apicapstone.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.account.PagingFormatAccountDto;
import project.apicapstone.dto.applicant.CreateApplicantDto;
import project.apicapstone.dto.applicant.PagingFormatApplicantDto;
import project.apicapstone.entity.Applicant;
import project.apicapstone.repository.ApplicantRepository;
import project.apicapstone.service.ApplicantService;

import java.util.List;

@Service
public class ApplicantServiceImpl implements ApplicantService {
    private ApplicantRepository applicantRepository;

    public ApplicantServiceImpl(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    @Override
    public List<Applicant> findAll() {
        return applicantRepository.findAll();
    }

    @Override
    public Applicant createApplicant(CreateApplicantDto dto) {
        Applicant newApplicant = new Applicant();
        newApplicant.setApplicantId(dto.getApplicantId());
        newApplicant.setApplicantName(dto.getApplicantName());
        newApplicant.setDateBirth(dto.getDateBirth());
        newApplicant.setAddress(dto.getAddress());
        newApplicant.setPhone(dto.getPhone());
        newApplicant.setGender(dto.getGender());
        newApplicant.setEmail(dto.getEmail());
        newApplicant.setCertification(dto.getCertification());
        newApplicant.setStatus(dto.getStatus());
        newApplicant.setResumeFile(dto.getResumeFile());
        return applicantRepository.save(newApplicant);
    }

    @Override
    public boolean isExisted(String s) {
        return applicantRepository.existsById(s);
    }

    @Override
    public Page<Applicant> findAllApplicant(Pageable pageable) {
        return applicantRepository.findAllApplicant(pageable);
    }

    @Override
    public Object pagingFormat(Page<Applicant> applicantPage) {
        PagingFormatApplicantDto dto = new PagingFormatApplicantDto();
        dto.setPageSize(applicantPage.getSize());
        dto.setTotalRecordCount(applicantPage.getTotalElements());
        dto.setPageNumber(applicantPage.getNumber());
        dto.setRecords(applicantPage.toList());
        return dto;
    }
}
