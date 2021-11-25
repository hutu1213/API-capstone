package project.apicapstone.controller;

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
@RequestMapping("/application")
public class ApplicantController {
    private ApplicantService applicantService;

    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @GetMapping("/get-all")
    public Object findAllApplicant() {
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
