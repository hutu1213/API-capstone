package project.apicapstone.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.applicant.ApplicantWithScoreDto;
import project.apicapstone.dto.applicant.CreateApplicantDto;
import project.apicapstone.dto.applicant.ProcessApplicantDto;
import project.apicapstone.dto.applicant.UpdateApplicantDto;
import project.apicapstone.dto.criteria.CriteriaWithoutJobPostingDto;
import project.apicapstone.entity.Applicant;
import project.apicapstone.service.ApplicantService;
import project.apicapstone.service.CriteriaService;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applicant")
//@CrossOrigin(origins = "*")
public class ApplicantController {
    private ApplicantService applicantService;
    @Autowired
    private CriteriaService criteriaService;

    public static final String APPLICANT_PROCESS_API = "https://applicant-process-api.herokuapp.com";

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
    public Object createApplicant(@Valid @RequestBody CreateApplicantDto dto,
                                  BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Applicant createApplicant = applicantService.createApplicant(dto);

        // send this new applicant to scan processor api
        callScanApplicantAPI(dto);

        return ResponseHandler.getResponse(createApplicant, HttpStatus.CREATED);
    }

    @GetMapping("/get-by-id/{id}")
    public Object findApplicantById(@PathVariable("id") String id) {
        Applicant applicant = applicantService.findApplicantById(id);
        return ResponseHandler.getResponse(applicant, HttpStatus.OK);
    }

    @GetMapping("/search/{paramSearch}")
    public Object findApplicantByNameOrId(@PathVariable String paramSearch) {
        List<Applicant> applicantList = applicantService.findApplicantByNameOrId(paramSearch);
        if (applicantList.isEmpty()) {
            return ResponseHandler.getErrors("Not found ", HttpStatus.NOT_FOUND);
        }
        return ResponseHandler.getResponse(applicantList, HttpStatus.OK);
    }

    @PutMapping()
    public Object updateApplicant(@Valid @RequestBody UpdateApplicantDto dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
        }
        applicantService.updateApplicant(dto, dto.getApplicantId());
        return ResponseHandler.getResponse(HttpStatus.OK);
    }

    @DeleteMapping()
    public Object deleteApplicant(@RequestParam(name = "id") String id) {
        applicantService.deleteById(id);
        return ResponseHandler.getResponse(HttpStatus.OK);
    }
    @GetMapping("/search-paging/{paramSearch}")
    public Object search(@PathVariable String paramSearch, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Applicant> applicantPage = applicantService.search(paramSearch, pageable);
        return ResponseHandler.getResponse(applicantService.pagingFormat(applicantPage), HttpStatus.OK);
    }

    public void callScanApplicantAPI(CreateApplicantDto dto){
        WebClient client = WebClient.create(APPLICANT_PROCESS_API);

        HashMap<String, String> bodyValues = new HashMap<>();

        bodyValues.put("applicantId", dto.getApplicantId());
        bodyValues.put("resumeFile", dto.getResumeFile());

        try {
            Mono<String> response = client
                .post()
                .uri(new URI(APPLICANT_PROCESS_API + "/applicant/scan"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bodyValues))
                .retrieve()
                .bodyToMono(String.class);

            response
                .doOnSuccess(result -> {
                    updateScanData(result);
                    callAnalyzeApplicantAPI(dto.getJobPostingId());
                })
                .doOnError(error -> System.out.println("error" + error))
                .subscribe();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateScanData(String jsonStr) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, String> json = objectMapper.readValue(jsonStr, new TypeReference<Map<String, String>>() {});
            applicantService.updateScanData(json.get("scanData"), json.get("applicantId"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void callAnalyzeApplicantAPI(String jobPostingId) {
        // get all criteriaList of jobPosting by jobPostingId
        List<CriteriaWithoutJobPostingDto> criteriaList = criteriaService.findAllByJobPostingId(jobPostingId);
        //System.out.println("criteriaList" + criteriaList);

        List<ProcessApplicantDto> applicants = applicantService.getAllProcessApplicantDtoByJobPosting(jobPostingId);
        //System.out.println("applicants" + applicants);

        // call analyze api
        WebClient client = WebClient.create(APPLICANT_PROCESS_API);

        MultiValueMap<String, Object> bodyValues = new LinkedMultiValueMap<>();

        bodyValues.addAll("criteriaList", criteriaList);
        bodyValues.addAll("applicants", applicants);

        try {
            Mono<String> response = client
                    .post()
                    .uri(new URI(APPLICANT_PROCESS_API + "/applicant/analyze"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(bodyValues))
                    .retrieve()
                    .bodyToMono(String.class);

            response
                    .doOnSuccess(result -> {
                        updateScoreApplicant(result);
                    })
                    .doOnError(error -> System.out.println("error" + error))
                    .subscribe();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateScoreApplicant(String jsonStr){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<ApplicantWithScoreDto> applicantsScoreList =
                    objectMapper.readValue(jsonStr, new TypeReference<List<ApplicantWithScoreDto>>() {});
            System.out.println(applicantsScoreList);
            for (ApplicantWithScoreDto applicantScore: applicantsScoreList) {
                System.out.println(applicantScore.getScore());
                applicantService.updateScore(applicantScore.getScore(), applicantScore.getApplicantId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
