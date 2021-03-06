package project.apicapstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import project.apicapstone.entity.Workplace;

import java.util.List;

@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace,String> {

    Workplace findByWorkplaceId(String id);

    @Query("SELECT c FROM Workplace c WHERE lower(c.name)  LIKE lower(concat('%', ?1,'%')) OR c.workplaceId LIKE %?1%")
    List<Workplace> findWorkplaceByNameOrId(String paramSearch);

    @Transactional(readOnly = true)
    @Query("SELECT e FROM Workplace e")
    Page<Workplace> findAllWorkplace(Pageable pageable);

    @Query("SELECT c FROM Workplace c WHERE lower(c.name)  LIKE lower(concat('%', ?1,'%')) OR c.workplaceId LIKE %?1%")
    Page<Workplace> search(String paramSearch, Pageable pageable);

    List<Workplace> findAllBySubareaSubareaId(String id);
}
