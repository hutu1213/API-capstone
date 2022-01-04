package project.apicapstone.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.account.PagingFormatAccountDto;
import project.apicapstone.dto.area.CreateAreaDto;
import project.apicapstone.dto.area.PagingFormatAreaDto;
import project.apicapstone.dto.area.UpdateAreaDto;
import project.apicapstone.entity.Area;
import project.apicapstone.repository.AreaRepository;
import project.apicapstone.service.AreaService;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    private AreaRepository areaRepository;

    public AreaServiceImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Override
    public List<Area> findAll() {
        return areaRepository.findAll();
    }

    @Override
    public Area createAccount(CreateAreaDto dto) {
        Area newArea = new Area();
        newArea.setAreaId(dto.getAreaId());
        newArea.setName(dto.getName());
        newArea.setDescription(dto.getDescription());
        return areaRepository.save(newArea);
    }

    @Override
    public boolean isExisted(String s) {
        return areaRepository.existsById(s);
    }

    @Override
    public void deleteById(String id) {
        areaRepository.deleteById(id);
    }

    @Override
    public void updateArea(UpdateAreaDto dto, String areaId) {
        Area area = areaRepository.getById(dto.getAreaId());
        area.setName(dto.getName());
        area.setDescription(dto.getDescription());
        areaRepository.save(area);
    }

    @Override
    public Area findAreaById(String id) {
        return areaRepository.getById(id);
    }

    @Override
    public List<Area> findAreaByNameOrId(String paramSearch) {
        return areaRepository.findAreaByNameOrId(paramSearch);
    }

    @Override
    public Page<Area> findAllArea(Pageable pageable) {
        return areaRepository.findAllArea(pageable);
    }

    @Override
    public Object pagingFormat(Page<Area> areaPage) {
        PagingFormatAreaDto dto = new PagingFormatAreaDto();
        dto.setPageSize(areaPage.getSize());
        dto.setTotalRecordCount(areaPage.getTotalElements());
        dto.setPageNumber(areaPage.getNumber());
        dto.setRecords(areaPage.toList());
        return dto;
    }
}
