package project.apicapstone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import project.apicapstone.dto.evaluation.CreateEvaluationDto;
import project.apicapstone.dto.evaluation.UpdateEvaluationDto;
import project.apicapstone.entity.Evaluation;

import java.util.List;

public interface EvaluationService {
    boolean isExisted(String s);

    Page<Evaluation> findAll(Pageable pageable);

    Object pagingFormat(Page<Evaluation> evaluationPage);

    Page<Evaluation> search(String paramSearch, Pageable pageable);

    List<Evaluation> findAllEvaluation();

    Evaluation findEvaluationById(String id);

    Evaluation addEvaluation(CreateEvaluationDto dto);

    void deleteById(String id);

    void updateEvaluation(UpdateEvaluationDto dto, String evaluationId);

    List<Evaluation> getByApplicantId(String id);
}
