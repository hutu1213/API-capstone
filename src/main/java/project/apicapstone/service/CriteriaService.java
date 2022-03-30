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

    Criteria findCriteriaById(Long id);

    Criteria addNewCriteria(CreateCriteriaDto dto);

    void deleteById(Long id);

    void updateCriteria(UpdateCriteriaDto dto, Long criteriaId);

    boolean isExisted(Long s);

    List<Criteria> getByJobPostingId(String id);

    Page<Criteria> getPagingByJobPostingId(Pageable pageable, String id);
}
