package project.apicapstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.apicapstone.entity.Account;
import project.apicapstone.entity.Dependant;
import project.apicapstone.entity.Employee;

import java.util.List;

@Repository
public interface DependantRepository extends JpaRepository<Dependant,String> {
    @Transactional(readOnly = true)
    @Query("SELECT e FROM Dependant e")
    Page<Dependant> findAllDependant(Pageable pageable);

    @Query("SELECT c FROM Dependant c WHERE lower(c.dependantName)  LIKE lower(concat('%', ?1,'%')) OR c.dependantId LIKE %?1%")
    List<Dependant> findDependantsByNameOrId(String paramSearch);


    @Query("SELECT c FROM Dependant c WHERE lower(c.dependantName)  LIKE lower(concat('%', ?1,'%')) OR c.dependantId LIKE %?1%")
    Page<Dependant> search(String paramSearch, Pageable pageable);

    Page<Dependant> getAllByEmployeeEmployeeId(String id,Pageable pageable);
}
