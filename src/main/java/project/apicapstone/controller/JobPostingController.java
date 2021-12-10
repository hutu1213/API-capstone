package project.apicapstone.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.jobPosting.CreateJobPostingDto;
import project.apicapstone.entity.JobPosting;
import project.apicapstone.service.JobPostingService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/jobPosting")
public class JobPostingController {
    private JobPostingService jobPostingService;

    public JobPostingController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }

    @GetMapping("/get-all")
    public Object getAll() {
        List<JobPosting> jobPostingList = jobPostingService.findAll();
        return ResponseHandler.getResponse(jobPostingList, HttpStatus.OK);
    }

    @GetMapping
    public Object findAllJobPosting(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<JobPosting> jobPostingPage = jobPostingService.findAllJobPosting(pageable);
        return ResponseHandler.getResponse(jobPostingService.pagingFormat(jobPostingPage), HttpStatus.OK);
    }

    @PostMapping
    public Object createJobPosting(@Valid @RequestBody CreateJobPostingDto dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }
        jobPostingService.createJP(dto);
        return ResponseHandler.getResponse(HttpStatus.CREATED);
    }
}
