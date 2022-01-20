package project.apicapstone.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.subarea.CreateSubareaDto;
import project.apicapstone.dto.subarea.PagingFormatSubareaDto;
import project.apicapstone.dto.subarea.UpdateSubareaDto;
import project.apicapstone.dto.workplace.PagingFormatWorkPlaceDto;
import project.apicapstone.entity.Subarea;
import project.apicapstone.repository.AreaRepository;
import project.apicapstone.repository.SubareaRepository;
import project.apicapstone.service.SubareaService;

import java.util.List;

@Service
public class SubareaServiceImpl implements SubareaService {
    private SubareaRepository subareaRepository;
    private AreaRepository areaRepository;

    public SubareaServiceImpl(SubareaRepository subareaRepository, AreaRepository areaRepository) {
        this.subareaRepository = subareaRepository;
        this.areaRepository = areaRepository;
    }

    @Override
    public boolean isExisted(String s) {
        return subareaRepository.existsById(s);
    }

    @Override
    public Subarea createSubarea(CreateSubareaDto dto) {
        Subarea newSubarea = new Subarea();
        newSubarea.setSubareaId(dto.getSubareaId());
        newSubarea.setName(dto.getName());
        newSubarea.setDescription(dto.getDescription());
        newSubarea.setArea(areaRepository.getById(dto.getAreaId()));
        return subareaRepository.save(newSubarea);
    }

    @Override
    public List<Subarea> findAll() {
        return subareaRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        subareaRepository.deleteById(id);
    }

    @Override
    public void updateSubarea(UpdateSubareaDto dto, String subareaId) {
        Subarea subarea = subareaRepository.getById(dto.getSubareaId());
        subarea.setName(dto.getName());
        subarea.setDescription(dto.getDescription());
        subarea.setArea(areaRepository.getById(dto.getAreaId()));
        subareaRepository.save(subarea);
    }

    @Override
    public Subarea findSubareaById(String id) {
        return subareaRepository.getById(id);
    }

    @Override
    public List<Subarea> findSubareaByNameOrId(String paramSearch) {
        return subareaRepository.findSubareaByNameOrId(paramSearch);
    }

    @Override
    public Page<Subarea> findAllSubarea(Pageable pageable) {
        return subareaRepository.findAllSubarea(pageable);
    }

    @Override
    public Object pagingFormat(Page<Subarea> subareaPage) {
        PagingFormatSubareaDto dto = new PagingFormatSubareaDto();
        dto.setPageSize(subareaPage.getSize());
        dto.setTotalRecordCount(subareaPage.getTotalElements());
        dto.setPageNumber(subareaPage.getNumber());
        dto.setRecords(subareaPage.toList());
        return dto;
    }

    @Override
    public List<Subarea> findSubareaByAreaId(String id) {
        return subareaRepository.findAllByAreaAreaId(id);
    }
}
