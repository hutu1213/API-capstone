package project.apicapstone.service.impl;

import org.springframework.stereotype.Service;
import project.apicapstone.dto.workplace.CreateWorkplaceDto;
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
}
