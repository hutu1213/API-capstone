package project.apicapstone.service.impl;

import org.springframework.stereotype.Service;
import project.apicapstone.repository.JobPostingRepository;
import project.apicapstone.service.JobPostingService;

@Service
public class JobPostingServiceImpl implements JobPostingService {
    private JobPostingRepository jobPostingRepository;
    public JobPostingServiceImpl(JobPostingRepository jobPostingRepository){
        this.jobPostingRepository=jobPostingRepository;
    }
}
