package project.apicapstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.apicapstone.dto.criteria.CriteriaWithoutJobPostingDto;
import org.springframework.transaction.annotation.Transactional;
import project.apicapstone.entity.Criteria;

import java.util.List;

@Repository
public interface CriteriaRepository extends JpaRepository<Criteria,String> {

    @Query("SELECT c.criteriaId AS criteriaId, " +
            "c.criteriaDescription AS criteriaDescription, " +
            "c.weight AS weight " +
            "FROM Criteria c LEFT OUTER JOIN JobPosting j " +
            "ON c.jobPosting.jobPostingId = j.jobPostingId " +
            "WHERE j.jobPostingId=?1")
    List<CriteriaWithoutJobPostingDto> getAllByJobPostingId(String jobPostingId);

    @Transactional(readOnly = true)
    @Query("SELECT e FROM Criteria e")
    Page<Criteria> findAllCriteria(Pageable pageable);

    @Query("SELECT c FROM Criteria c join c.jobPosting j where j.jobPostingId=?1")
    List<Criteria> getByJPId(String id);
}
