package project.apicapstone.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.criteria.CriteriaWithoutJobPostingDto;
import project.apicapstone.dto.criteria.CreateCriteriaDto;
import project.apicapstone.dto.criteria.PagingFormatCriteriaDto;
import project.apicapstone.dto.criteria.UpdateCriteriaDto;
import project.apicapstone.entity.Criteria;
import project.apicapstone.repository.CriteriaRepository;
import project.apicapstone.repository.JobPostingRepository;
import project.apicapstone.service.CriteriaService;
import java.util.List;

@Service
public class CriteriaServiceImpl implements CriteriaService {
    private CriteriaRepository criteriaRepository;
    private JobPostingRepository jobPostingRepository;
    public CriteriaServiceImpl(CriteriaRepository criteriaRepository,JobPostingRepository jobPostingRepository){
        this.criteriaRepository=criteriaRepository;
        this.jobPostingRepository=jobPostingRepository;
    }

    @Override
    public Page<Criteria> findAll(Pageable pageable) {
        return criteriaRepository.findAllCriteria(pageable);
    }

    @Override
    public Object pagingFormat(Page<Criteria> criteriaPage) {
        PagingFormatCriteriaDto dto = new PagingFormatCriteriaDto();
        dto.setPageSize(criteriaPage.getSize());
        dto.setTotalRecordCount(criteriaPage.getTotalElements());
        dto.setPageNumber(criteriaPage.getNumber());
        dto.setRecords(criteriaPage.toList());
        return dto;
    }

    @Override
    public List<Criteria> findAllCriteria() {
        return criteriaRepository.findAll();
    }

    @Override
    public Criteria findCriteriaById(String id) {
        return criteriaRepository.getById(id);
    }

    @Override
    public Criteria addNewCriteria(CreateCriteriaDto dto) {
        Criteria criteria=new Criteria();
        criteria.setCriteriaId(dto.getCriteriaId());
        criteria.setCriteriaDescription(dto.getCriteriaDescription());
        criteria.setWeight(dto.getWeight());
        criteria.setJobPosting(jobPostingRepository.getById(dto.getJobPostingId()));

        return criteriaRepository.save(criteria);
    }

    @Override
    public void deleteById(String id) {
        criteriaRepository.deleteById(id);
    }

    @Override
    public void updateCriteria(UpdateCriteriaDto dto, String criteriaId) {
        Criteria criteria= criteriaRepository.getById(criteriaId);

        criteria.setCriteriaDescription(dto.getCriteriaDescription());
        criteria.setWeight(dto.getWeight());
        criteria.setJobPosting(jobPostingRepository.getById(dto.getJobPostingId()));
        criteriaRepository.save(criteria);

    }

    @Override
    public boolean isExisted(String s) {
        return criteriaRepository.existsById(s);
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
