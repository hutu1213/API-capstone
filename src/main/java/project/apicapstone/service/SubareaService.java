package project.apicapstone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.apicapstone.dto.subarea.CreateSubareaDto;
import project.apicapstone.dto.subarea.UpdateSubareaDto;
import project.apicapstone.entity.Subarea;

import java.util.List;

public interface SubareaService {
    boolean isExisted(String s);

    Subarea createSubarea(CreateSubareaDto dto);

    List<Subarea> findAll();

    void deleteById(String id);

    void updateSubarea(UpdateSubareaDto dto, String subareaId);

    Subarea findSubareaById(String id);

    List<Subarea> findSubareaByNameOrId(String paramSearch);

    Page<Subarea> findAllSubarea(Pageable pageable);

    Object pagingFormat(Page<Subarea> subareaPage);

    List<Subarea> findSubareaByAreaId(String id);
}
