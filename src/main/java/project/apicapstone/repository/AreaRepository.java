package project.apicapstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.apicapstone.entity.Applicant;
import project.apicapstone.entity.Area;
import project.apicapstone.entity.Workplace;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area,String> {
    @Query("SELECT e FROM Area e WHERE e.name LIKE %?1% OR e.areaId LIKE %?1%")
    List<Area> findAreaByNameOrId(String paramSearch);

    @Transactional(readOnly = true)
    @Query("SELECT e FROM Area e")
    Page<Area> findAllArea(Pageable pageable);

    @Query("SELECT e FROM Area e WHERE e.name LIKE %?1% OR e.areaId LIKE %?1%")
    Page<Area> search(String paramSearch,Pageable pageable);
}
