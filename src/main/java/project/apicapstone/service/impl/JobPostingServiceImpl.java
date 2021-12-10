package project.apicapstone.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.jobPosting.CreateJobPostingDto;
import project.apicapstone.dto.jobPosting.PagingFormatJobPostingDto;
import project.apicapstone.entity.JobPosting;
import project.apicapstone.entity.Title;
import project.apicapstone.repository.JobPostingRepository;
import project.apicapstone.repository.TitleRepository;
import project.apicapstone.service.JobPostingService;

import java.util.List;

@Service
public class JobPostingServiceImpl implements JobPostingService {
    private JobPostingRepository jobPostingRepository;
    private TitleRepository titleRepository;

    public JobPostingServiceImpl(JobPostingRepository jobPostingRepository, TitleRepository titleRepository) {
        this.jobPostingRepository = jobPostingRepository;
        this.titleRepository = titleRepository;
    }

    @Override
    public boolean isExisted(String s) {
        return jobPostingRepository.existsById(s);
    }

    @Override
    public void createJP(CreateJobPostingDto dto) {
        JobPosting jobPosting = new JobPosting();
        jobPosting.setJobPostingId(dto.getJobPostingId());
        jobPosting.setVacancies(dto.getVacancies());
        jobPosting.setDatePost(dto.getDatePost());
        jobPosting.setEmploymentInfor(dto.getEmploymentInfor());
        jobPosting.setJobDescription(dto.getJobDescription());
        jobPosting.setJobRequirements(dto.getJobRequirements());
        Title title = titleRepository.getById(dto.getTitleId());
        jobPosting.setTitle(title);
        jobPostingRepository.save(jobPosting);
    }

    @Override
    public List<JobPosting> findAll() {
        return jobPostingRepository.findAll();
    }

    @Override
    public Page<JobPosting> findAllJobPosting(Pageable pageable) {
        return jobPostingRepository.findAllJobPosting(pageable);
    }

    @Override
    public Object pagingFormat(Page<JobPosting> jobPostingPage) {
        PagingFormatJobPostingDto dto = new PagingFormatJobPostingDto();
        dto.setPageSize(jobPostingPage.getSize());
        dto.setTotalRecordCount(jobPostingPage.getTotalElements());
        dto.setPageNumber(jobPostingPage.getNumber());
        dto.setRecords(jobPostingPage.toList());
        return dto;
    }
}
