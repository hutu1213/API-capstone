package project.apicapstone.service.impl;

import org.springframework.stereotype.Service;
import project.apicapstone.dto.criteria.CriteriaWithoutJobPostingDto;
import project.apicapstone.entity.Criteria;
import project.apicapstone.repository.CriteriaRepository;
import project.apicapstone.service.CriteriaService;

import java.util.List;

@Service
public class CriteriaServiceImpl implements CriteriaService {
    private CriteriaRepository criteriaRepository;
    public CriteriaServiceImpl(CriteriaRepository criteriaRepository){
        this.criteriaRepository=criteriaRepository;
    }

    @Override
    public List<Criteria> findAll() {
        return criteriaRepository.findAll();
    }

    @Override
    public List<CriteriaWithoutJobPostingDto> findAllByJobPostingId(String jobPostingId) {
        return criteriaRepository.getAllByJobPostingId(jobPostingId);
    }
}
