package project.apicapstone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.apicapstone.dto.area.CreateAreaDto;
import project.apicapstone.dto.area.UpdateAreaDto;
import project.apicapstone.entity.Area;

import java.util.List;

public interface AreaService {
    List<Area> findAll();

    Area createAccount(CreateAreaDto dto);

    boolean isExisted(String s);

    void deleteById(String id);

    void updateArea(UpdateAreaDto dto, String areaId);

    Area findAreaById(String id);

    List<Area> findAreaByNameOrId(String paramSearch);

    Page<Area> findAllArea(Pageable pageable);

    Object pagingFormat(Page<Area> areaPage);
}
