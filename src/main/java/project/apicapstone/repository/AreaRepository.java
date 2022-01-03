package project.apicapstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.apicapstone.entity.Area;
import project.apicapstone.entity.Workplace;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area,String> {
    @Query("SELECT e FROM Area e WHERE e.name LIKE %?1% OR e.areaId LIKE %?1%")
    List<Area> findAreaByNameOrId(String paramSearch);
}
