package project.apicapstone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.apicapstone.dto.workplace.CreateWorkplaceDto;
import project.apicapstone.dto.workplace.UpdateWorkplaceDto;
import project.apicapstone.entity.Workplace;

import java.util.List;

public interface WorkplaceService {
    List<Workplace> findAll();

    Workplace createWorkplace(CreateWorkplaceDto dto);

    boolean isExisted(String s);

    void deleteById(String id);

    void updateWorkplace(UpdateWorkplaceDto dto, String workplaceId);

    Workplace findWorkplaceById(String id);

    List<Workplace> findWorkplaceByNameOrId(String paramSearch);

    Page<Workplace> findAllWorkplace(Pageable pageable);

    Object pagingFormat(Page<Workplace> workplacePage);

    List<Workplace> getBySubarea(String id);

    Page<Workplace> search(String paramSearch, Pageable pageable);
}
