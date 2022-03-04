package project.apicapstone.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import project.apicapstone.dto.applicant.ApplicantWithScoreDto;
import project.apicapstone.dto.applicant.CreateApplicantDto;
import project.apicapstone.dto.applicant.ProcessApplicantDto;
import project.apicapstone.dto.criteria.CriteriaWithoutJobPostingDto;

import project.apicapstone.service.ApplicantService;
import project.apicapstone.service.CriteriaService;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ScanApplicant {
    private Logger logger = LoggerFactory.getLogger(ScanApplicant.class);
    public static final String APPLICANT_PROCESS_API = "https://applicant-process-api.herokuapp.com";
    private ApplicantService applicantService;
    private CriteriaService criteriaService;

    public ScanApplicant(ApplicantService applicantService, CriteriaService criteriaService) {
        this.applicantService = applicantService;
        this.criteriaService = criteriaService;
    }

    public void callScanApplicantAPI(CreateApplicantDto dto) {
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

    public void updateScanData(String jsonStr) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, String> json = objectMapper.readValue(jsonStr, new TypeReference<Map<String, String>>() {
            });// readValue() convert Json String to Java Object(Map)
            applicantService.updateScanData(json.get("scanData"), json.get("applicantId"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void callAnalyzeApplicantAPI(String jobPostingId) {
        // get all criteriaList of jobPosting by jobPostingId
        List<CriteriaWithoutJobPostingDto> criteriaList = criteriaService.findAllByJobPostingId(jobPostingId);
        // sout ra xem co key work khong
        List<ProcessApplicantDto> applicants = applicantService.getAllProcessApplicantDtoByJobPosting(jobPostingId);

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

    public void updateScoreApplicant(String jsonStr) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<ApplicantWithScoreDto> applicantsScoreList =
                    objectMapper.readValue(jsonStr, new TypeReference<List<ApplicantWithScoreDto>>() {
                    });

            logger.info(String.valueOf(applicantsScoreList));
            for (ApplicantWithScoreDto applicantScore : applicantsScoreList) {
                //System.out.println("core: " + applicantScore.getScore());
                logger.info("core: " + applicantScore.getScore());
                applicantService.updateScore(applicantScore.getScore(), applicantScore.getApplicantId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
