package project.apicapstone.service;

import project.apicapstone.dto.criteria.CriteriaWithoutJobPostingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.apicapstone.dto.criteria.CreateCriteriaDto;
import project.apicapstone.dto.criteria.UpdateCriteriaDto;
import project.apicapstone.entity.Criteria;

import java.util.List;

public interface CriteriaService {
  
    List<CriteriaWithoutJobPostingDto> findAllByJobPostingId(String jobPostingId);

    Page<Criteria> findAll(Pageable pageable);

    Object pagingFormat(Page<Criteria> criteriaPage);

    List<Criteria> findAllCriteria();

    Criteria findCriteriaById(String id);

    Criteria addNewCriteria(CreateCriteriaDto dto);

    void deleteById(String id);

    void updateCriteria(UpdateCriteriaDto dto, String criteriaId);

    boolean isExisted(String s);
}
