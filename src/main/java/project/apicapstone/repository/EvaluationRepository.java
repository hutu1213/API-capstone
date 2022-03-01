package project.apicapstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.apicapstone.entity.Employee;
import project.apicapstone.entity.Evaluation;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation,String> {
    @Transactional(readOnly = true)
    @Query("SELECT e FROM Evaluation e")
    Page<Evaluation> findAllEvaluation(Pageable pageable);

    @Query("SELECT e FROM Evaluation e WHERE lower(e.content)  LIKE lower(concat('%', ?1,'%'))  OR e.evaluationId LIKE %?1%")
    Page<Evaluation> search(String paramSearch, Pageable pageable);

    List<Evaluation> findAllByApplicantApplicantId(String id);

}
