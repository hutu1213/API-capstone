package project.apicapstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.apicapstone.entity.Account;
import project.apicapstone.entity.Dependant;
@Repository
public interface DependantRepository extends JpaRepository<Dependant,String> {
    @Transactional(readOnly = true)
    @Query("SELECT e FROM Dependant e")
    Page<Dependant> findAllDependant(Pageable pageable);
}
