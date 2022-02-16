package project.apicapstone.service;

import project.apicapstone.dto.criteria.CriteriaWithoutJobPostingDto;
import project.apicapstone.entity.Criteria;

import java.util.List;

public interface CriteriaService {
    List<Criteria> findAll();

    List<CriteriaWithoutJobPostingDto> findAllByJobPostingId(String jobPostingId);
}
