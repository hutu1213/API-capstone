package project.apicapstone.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.recruitmentRequest.CreateRecruitmentRequestDto;
import project.apicapstone.dto.recruitmentRequest.UpdateRecruitmentRequestDto;
import project.apicapstone.entity.RecruitmentRequest;
import project.apicapstone.service.RecruitmentRequestService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/api/recruitmentRequest")
public class RecruitmentRequestController {
    private final RecruitmentRequestService recruitmentRequestService;

    public RecruitmentRequestController(RecruitmentRequestService recruitmentRequestService) {
        this.recruitmentRequestService = recruitmentRequestService;
    }

    @GetMapping
    public Object findAllPosition(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<RecruitmentRequest> recruitmentRequestPage = recruitmentRequestService.findAllRecruitmentRequest(pageable);
        return ResponseHandler.getResponse(recruitmentRequestService.pagingFormat(recruitmentRequestPage), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public Object findAll() {
        List<RecruitmentRequest> recruitmentRequest = recruitmentRequestService.findAll();
        return ResponseHandler.getResponse(recruitmentRequest, HttpStatus.OK);
    }

    @PostMapping("/create-recruitmentRequest")
    public Object createRecruitmentRequest(@Valid @RequestBody CreateRecruitmentRequestDto dto, BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        RecruitmentRequest createRecruitmentRequest = recruitmentRequestService.createRecruitmentRequest(dto);

        return ResponseHandler.getResponse(createRecruitmentRequest, HttpStatus.CREATED);
    }

    @GetMapping("/get-by-id/{id}")
    public Object findRecruitmentRequestById(@PathVariable("id") Long id) {
        RecruitmentRequest recruitmentRequest = recruitmentRequestService.findRecruitmentRequestById(id);
        return ResponseHandler.getResponse(recruitmentRequest, HttpStatus.OK);
    }

    @DeleteMapping()
    public Object deleteRecruitmentRequest(@RequestParam(name = "id") Long id) {
        recruitmentRequestService.deleteById(id);
        return ResponseHandler.getResponse(HttpStatus.OK);
    }

    @PutMapping()
    public Object updateRecruitmentRequest(@Valid @RequestBody UpdateRecruitmentRequestDto dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }
        recruitmentRequestService.updateRecruitmentRequest(dto, dto.getRecruitmentRequestId());
        return ResponseHandler.getResponse(HttpStatus.OK);
    }

    @GetMapping("/get-by-employee-id/{id}")
    public Object findRecruitmentRequestByEmployeeId(@PathVariable("id") String id, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<RecruitmentRequest> recruitmentRequestPage = recruitmentRequestService.findRecruitmentRequestByEmployeeId(id, pageable);
        return ResponseHandler.getResponse(recruitmentRequestService.pagingFormat(recruitmentRequestPage), HttpStatus.OK);
    }

    @GetMapping("/get-by-status/{status}")
    public Object findRecruitmentRequestByStatus(@PathVariable("status") String status, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<RecruitmentRequest> recruitmentRequestPage = recruitmentRequestService.findRecruitmentRequestByStatus(status, pageable);
        return ResponseHandler.getResponse(recruitmentRequestService.pagingFormat(recruitmentRequestPage), HttpStatus.OK);
    }

    @GetMapping("/search/{paramSearch}")
    public Object findByNameOrId(@PathVariable Long paramSearch) {
        RecruitmentRequest employeeList = recruitmentRequestService.searchById(paramSearch);
        return ResponseHandler.getResponse(employeeList, HttpStatus.OK);
    }
}
