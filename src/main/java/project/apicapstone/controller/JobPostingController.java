package project.apicapstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.apicapstone.service.JobPostingService;

@RestController
@RequestMapping(value = "/jobPosting")
public class JobPostingController {
    private JobPostingService jobPostingService;

    public JobPostingController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }
}
