package project.apicapstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import project.apicapstone.entity.Criteria;

@Repository
public interface CriteriaRepository extends JpaRepository<Criteria,String> {
    @Transactional(readOnly = true)
    @Query("SELECT e FROM Criteria e")
    Page<Criteria> findAllCriteria(Pageable pageable);
}
