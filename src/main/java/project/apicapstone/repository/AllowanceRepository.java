package project.apicapstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.apicapstone.entity.Allowance;

@Repository
public interface AllowanceRepository extends JpaRepository<Allowance,String> {
    @Transactional(readOnly = true)
    @Query("SELECT e FROM Allowance e")
    Page<Allowance> findAllAllowance(Pageable pageable);
}
