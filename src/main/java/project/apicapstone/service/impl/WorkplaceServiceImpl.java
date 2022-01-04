package project.apicapstone.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.account.PagingFormatAccountDto;
import project.apicapstone.dto.workplace.CreateWorkplaceDto;
import project.apicapstone.dto.workplace.PagingFormatWorkPlaceDto;
import project.apicapstone.dto.workplace.UpdateWorkplaceDto;
import project.apicapstone.entity.Workplace;
import project.apicapstone.repository.SubareaRepository;
import project.apicapstone.repository.WorkplaceRepository;
import project.apicapstone.service.WorkplaceService;

import java.util.List;

@Service
public class WorkplaceServiceImpl implements WorkplaceService {
    private WorkplaceRepository workplaceRepository;
    private SubareaRepository subareaRepository;

    private WorkplaceServiceImpl(WorkplaceRepository workplaceRepository, SubareaRepository subareaRepository) {
        this.workplaceRepository = workplaceRepository;
        this.subareaRepository = subareaRepository;
    }

    @Override
    public List<Workplace> findAll() {
        return workplaceRepository.findAll();
    }

    @Override
    public Workplace createWorkplace(CreateWorkplaceDto dto) {
        Workplace newWorkplace = new Workplace();
        newWorkplace.setWorkplaceId(dto.getWorkplaceId());
        newWorkplace.setName(dto.getName());
        newWorkplace.setType(dto.getType());
        newWorkplace.setAddress(dto.getAddress());
        newWorkplace.setSubarea(subareaRepository.getById(dto.getSubareaId()));
        return workplaceRepository.save(newWorkplace);
    }

    @Override
    public boolean isExisted(String s) {
        return workplaceRepository.existsById(s);
    }

    @Override
    public void deleteById(String id) {
        workplaceRepository.deleteById(id);
    }

    @Override
    public void updateWorkplace(UpdateWorkplaceDto dto, String workplaceId) {
        Workplace workplace = workplaceRepository.getById(dto.getWorkplaceId());
        workplace.setName(dto.getName());
        workplace.setType(dto.getType());
        workplace.setAddress(dto.getAddress());
        workplace.setSubarea(subareaRepository.getById(dto.getSubareaId()));
        workplaceRepository.save(workplace);
    }

    @Override
    public Workplace findWorkplaceById(String id) {
        return workplaceRepository.getById(id);
    }

    @Override
    public List<Workplace> findWorkplaceByNameOrId(String paramSearch) {
        return workplaceRepository.findWorkplaceByNameOrId(paramSearch);
    }

    @Override
    public Page<Workplace> findAllWorkplace(Pageable pageable) {
        return workplaceRepository.findAllWorkplace(pageable);
    }

    @Override
    public Object pagingFormat(Page<Workplace> workplacePage) {
        PagingFormatWorkPlaceDto dto = new PagingFormatWorkPlaceDto();
        dto.setPageSize(workplacePage.getSize());
        dto.setTotalRecordCount(workplacePage.getTotalElements());
        dto.setPageNumber(workplacePage.getNumber());
        dto.setRecords(workplacePage.toList());
        return dto;
    }
}
