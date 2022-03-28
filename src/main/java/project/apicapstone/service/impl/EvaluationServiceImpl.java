package project.apicapstone.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.evaluation.CreateEvaluationDto;
import project.apicapstone.dto.evaluation.PagingFormatEvaluationDto;
import project.apicapstone.dto.evaluation.UpdateEvaluationDto;
import project.apicapstone.entity.Evaluation;
import project.apicapstone.repository.ApplicantRepository;
import project.apicapstone.repository.EmployeeRepository;
import project.apicapstone.repository.EvaluationRepository;
import project.apicapstone.service.EvaluationService;

import java.time.LocalDate;
import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {
    private EvaluationRepository evaluationRepository;
    private ApplicantRepository applicantRepository;
    private EmployeeRepository employeeRepository;

    public EvaluationServiceImpl(EvaluationRepository evaluationRepository, ApplicantRepository applicantRepository, EmployeeRepository employeeRepository) {
        this.evaluationRepository = evaluationRepository;
        this.applicantRepository = applicantRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean isExisted(String s) {
        return evaluationRepository.existsById(s);
    }

    @Override
    public Page<Evaluation> findAll(Pageable pageable) {
        return evaluationRepository.findAllEvaluation(pageable);
    }

    @Override
    public Object pagingFormat(Page<Evaluation> evaluationPage) {
        PagingFormatEvaluationDto dto = new PagingFormatEvaluationDto();
        dto.setPageSize(evaluationPage.getSize());
        dto.setTotalRecordCount(evaluationPage.getTotalElements());
        dto.setPageNumber(evaluationPage.getNumber());
        dto.setRecords(evaluationPage.toList());
        return dto;
    }

    @Override
    public Page<Evaluation> search(String paramSearch, Pageable pageable) {
        return evaluationRepository.search(paramSearch, pageable);
    }

    @Override
    public List<Evaluation> findAllEvaluation() {
        return evaluationRepository.findAll();
    }

    @Override
    public Evaluation findEvaluationById(String id) {
        return evaluationRepository.getById(id);
    }

    @Override
    public Evaluation addEvaluation(CreateEvaluationDto dto) {
        Evaluation evaluation = new Evaluation();
        evaluation.setEvaluationId(dto.getEvaluationId());
        evaluation.setContent(dto.getContent());
        evaluation.setType(dto.getType());
        evaluation.setCreateDate(LocalDate.now());
        evaluation.setRating(dto.getRating());
        evaluation.setCreateTime(dto.getCreateTime());
        evaluation.setUpdateDate(LocalDate.now());
        evaluation.setApplicant(applicantRepository.getById(dto.getApplicantId()));
        evaluation.setEmployee(employeeRepository.getById(dto.getEmployeeId()));
        return evaluationRepository.save(evaluation);
    }

    @Override
    public void deleteById(String id) {
        evaluationRepository.deleteById(id);
    }

    @Override
    public void updateEvaluation(UpdateEvaluationDto dto, String evaluationId) {
        Evaluation evaluation = evaluationRepository.getById(evaluationId);
        evaluation.setContent(dto.getContent());
        evaluation.setType(dto.getType());
        evaluation.setUpdateDate(LocalDate.now());
        evaluation.setRating(dto.getRating());
        evaluation.setCreateTime(dto.getCreateTime());
        evaluation.setApplicant(applicantRepository.getById(dto.getApplicantId()));
        evaluation.setEmployee(employeeRepository.getById(dto.getEmployeeId()));
        evaluationRepository.save(evaluation);
    }

    @Override
    public List<Evaluation> getByApplicantId(String id) {
        return evaluationRepository.findAllByApplicantApplicantId(id);
    }
}
