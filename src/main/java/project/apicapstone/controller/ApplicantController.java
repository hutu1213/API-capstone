package project.apicapstone.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.account.CreateAccountDto;
import project.apicapstone.dto.applicant.CreateApplicantDto;
import project.apicapstone.entity.Account;
import project.apicapstone.entity.Applicant;
import project.apicapstone.service.ApplicantService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/applicant")
public class ApplicantController {
    private ApplicantService applicantService;

    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }
    @GetMapping
    public Object findAllApplicant(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Applicant> applicantPage = applicantService.findAllApplicant(pageable);
        return ResponseHandler.getResponse(applicantService.pagingFormat(applicantPage), HttpStatus.OK);
    }
    @GetMapping("/get-all")
    public Object findAll() {
        List<Applicant> applicants = applicantService.findAll();
        return ResponseHandler.getResponse(applicants, HttpStatus.OK);
    }

    @PostMapping("/create-applicant")
    public Object createUser(@Valid @RequestBody CreateApplicantDto dto,
                             BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Applicant newApplicant = applicantService.createApplicant(dto);

        return ResponseHandler.getResponse(newApplicant, HttpStatus.OK);
    }
}
