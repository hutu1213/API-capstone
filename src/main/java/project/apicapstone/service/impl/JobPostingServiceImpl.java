package project.apicapstone.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.jobPosting.CreateJobPostingDto;
import project.apicapstone.dto.jobPosting.PagingFormatJobPostingDto;
import project.apicapstone.dto.jobPosting.UpdateJobPostingDto;
import project.apicapstone.entity.JobPosting;
import project.apicapstone.entity.Title;
import project.apicapstone.repository.JobPostingRepository;
import project.apicapstone.repository.TitleRepository;
import project.apicapstone.service.JobPostingService;

import java.time.LocalDate;
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
    public JobPosting createJP(CreateJobPostingDto dto) {
        JobPosting newJobPosting = new JobPosting();
        newJobPosting.setJobPostingId(dto.getJobPostingId());
        newJobPosting.setDatePost(dto.getDatePost());
        newJobPosting.setCreateDate(LocalDate.now());
        newJobPosting.setJobDescription(dto.getJobDescription());
        newJobPosting.setJobRequirements(dto.getJobRequirements());
        newJobPosting.setStatus(dto.getStatus());
        newJobPosting.setBenefit(dto.getBenefit());
        newJobPosting.setPostTitle(dto.getPostTitle());
        Title title = titleRepository.getById(dto.getTitleId());
        newJobPosting.setTitle(title);
       return jobPostingRepository.save(newJobPosting);
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

    @Override
    public JobPosting findJobPostingById(String id) {
        return jobPostingRepository.getById(id);
    }

    @Override
    public List<JobPosting> findJobPostingsByIdOrDescriptionOrVacancies(String paramSearch) {
        //return jobPostingRepository.findJobPostingsByIdAndDescriptionAndVacancies(paramSearch);
        return null;
    }

    @Override
    public Page<JobPosting> search(String paramSearch, Pageable pageable) {
        return jobPostingRepository.search(paramSearch,pageable);
    }

    @Override
    public void deleteById(String id) {
        jobPostingRepository.deleteById(id);
    }

    @Override
    public void updateJobPosting(UpdateJobPostingDto dto, String jobPostingId) {
        JobPosting updateJobPosting = jobPostingRepository.getById(jobPostingId);
        updateJobPosting.setDatePost(dto.getDatePost());
        updateJobPosting.setJobDescription(dto.getJobDescription());
        updateJobPosting.setJobRequirements(dto.getJobRequirements());
        updateJobPosting.setStatus(dto.getStatus());
        updateJobPosting.setBenefit(dto.getBenefit());
        updateJobPosting.setPostTitle(dto.getPostTitle());
        Title title = titleRepository.getById(dto.getTitleId());
        updateJobPosting.setTitle(title);
        jobPostingRepository.save(updateJobPosting);
    }
}
