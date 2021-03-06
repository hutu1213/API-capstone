package project.apicapstone.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import project.apicapstone.dto.applicant.CreateApplicantDto;
import project.apicapstone.dto.applicant.PagingFormatApplicantDto;
import project.apicapstone.dto.applicant.ProcessApplicantDto;
import project.apicapstone.dto.applicant.UpdateApplicantDto;
import project.apicapstone.entity.Applicant;
import project.apicapstone.entity.JobPosting;
import project.apicapstone.repository.ApplicantRepository;
import project.apicapstone.repository.JobPostingRepository;
import project.apicapstone.service.ApplicantService;


import java.util.List;

@Service
public class ApplicantServiceImpl implements ApplicantService {
    private ApplicantRepository applicantRepository;
    private JobPostingRepository jobPostingRepository;

    public ApplicantServiceImpl(ApplicantRepository applicantRepository, JobPostingRepository jobPostingRepository) {
        this.applicantRepository = applicantRepository;
        this.jobPostingRepository = jobPostingRepository;
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
        newApplicant.setCheckSendMail(0);
        newApplicant.setPhone(dto.getPhone());
        newApplicant.setGender(dto.getGender());
        newApplicant.setEmail(dto.getEmail());
        newApplicant.setCertification(dto.getCertification());
        newApplicant.setStatus(dto.getStatus());
        newApplicant.setResumeFile(dto.getResumeFile());
        //newApplicant.setScanData(dto.getScanData());
        newApplicant.setScore(dto.getScore());
        newApplicant.setStage(dto.getStage());
        newApplicant.setInterviewDate(dto.getInterviewDate());
        newApplicant.setInterviewTime(dto.getInterviewTime());
        JobPosting jobPosting = jobPostingRepository.getById(dto.getJobPostingId());
        newApplicant.setJobPosting(jobPosting);
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

    @Override
    public Applicant findApplicantById(String id) {
        return applicantRepository.getById(id);
    }

    @Transactional
    @Override
    public List<Applicant> findApplicantByNameOrId(String paramSearch) {
        return applicantRepository.findApplicantsByNameOrId(paramSearch);
    }

    @Override
    public void updateApplicant(UpdateApplicantDto dto, String applicantId) {
        Applicant applicant = applicantRepository.getById(applicantId);

        applicant.setApplicantName(dto.getApplicantName());
        applicant.setDateBirth(dto.getDateBirth());
        applicant.setCheckSendMail(applicant.getCheckSendMail());
        applicant.setAddress(dto.getAddress());
        applicant.setPhone(dto.getPhone());
        applicant.setGender(dto.getGender());
        applicant.setEmail(dto.getEmail());
        applicant.setCertification(dto.getCertification());
        applicant.setStatus(dto.getStatus());
        applicant.setResumeFile(dto.getResumeFile());
        //applicant.setScanData(dto.getScanData());
        applicant.setScore(dto.getScore());
        applicant.setStage(dto.getStage());
        applicant.setInterviewDate(dto.getInterviewDate());
        applicant.setInterviewTime(dto.getInterviewTime());
        JobPosting jobPosting = jobPostingRepository.getById(dto.getJobPostingId());
        applicant.setJobPosting(jobPosting);
        applicantRepository.save(applicant);
    }

    @Override
    public void deleteById(String id) {
        applicantRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Page<Applicant> search(String paramSearch, Pageable pageable) {
        return applicantRepository.search(paramSearch,pageable);
    }

    @Override
    public List<ProcessApplicantDto> getAllProcessApplicantDtoByJobPosting(String jobPostingId) {
        return applicantRepository.getAllProcessApplicantsByJobPostingId(jobPostingId);
    }

    @Override
    public int updateScanData(String scanData, String id) {
        return applicantRepository.updateApplicantScanData(scanData, id);
    }

    @Override
    public int updateScore(Double score, String id) {
        return applicantRepository.updateApplicantScore(score, id);
    }
    @Override
    public List<Applicant> getAllByStatus(String status) {
        return applicantRepository.findAllByStatusAndCheckSendMail(status,0);

    }
}
