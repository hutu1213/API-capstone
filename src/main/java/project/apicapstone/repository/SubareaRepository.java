package project.apicapstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.apicapstone.entity.Area;
import project.apicapstone.entity.Position;
import project.apicapstone.entity.Subarea;
import project.apicapstone.entity.Workplace;

import java.util.List;

@Repository
public interface SubareaRepository extends JpaRepository<Subarea,String> {

    @Query("SELECT e FROM Subarea e WHERE e.name LIKE %?1% OR e.subareaId LIKE %?1%")
    List<Subarea> findSubareaByNameOrId(String paramSearch);
    @Transactional(readOnly = true)
    @Query("SELECT e FROM Subarea e")
    Page<Subarea> findAllSubarea(Pageable pageable);

    @Query("SELECT e FROM Subarea e WHERE e.name LIKE %?1% OR e.subareaId LIKE %?1%")
    Page<Subarea> search(String paramSearch, Pageable pageable);

    List<Subarea> findAllByAreaAreaId(String id);
}
