package project.apicapstone.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.allowance.CreateAllowanceDto;
import project.apicapstone.dto.applicant.CreateApplicantDto;
import project.apicapstone.entity.Allowance;
import project.apicapstone.entity.Applicant;
import project.apicapstone.entity.Employee;
import project.apicapstone.service.AllowanceService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/allowance")
public class AllowanceController {
    private AllowanceService allowanceService;

    public AllowanceController(AllowanceService allowanceService) {
        this.allowanceService = allowanceService;
    }

    @GetMapping
    public Object findAllAllowance(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Allowance> allowancePage = allowanceService.findAllAllowance(pageable);
        return ResponseHandler.getResponse(allowanceService.pagingFormat(allowancePage), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public Object findAll() {
        List<Allowance> allowances = allowanceService.findAll();
        return ResponseHandler.getResponse(allowances, HttpStatus.OK);
    }

    @PostMapping("/create-allowance")
    public Object createUser(@Valid @RequestBody CreateAllowanceDto dto,
                             BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Allowance newAllowance = allowanceService.createAllowance(dto);

        return ResponseHandler.getResponse(newAllowance, HttpStatus.OK);
    }
}
