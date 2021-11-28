package project.apicapstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.apicapstone.entity.Account;
import project.apicapstone.entity.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position,String> {
    @Transactional(readOnly = true)
    @Query("SELECT e FROM Position e")
    Page<Position> findAllPosition(Pageable pageable);
}
