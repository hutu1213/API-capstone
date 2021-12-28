package project.apicapstone.service.impl;

import org.springframework.stereotype.Service;
import project.apicapstone.repository.SubareaRepository;
import project.apicapstone.service.SubareaService;

@Service
public class SubareaServiceImpl implements SubareaService {
    private SubareaRepository subareaRepository;

    public SubareaServiceImpl(SubareaRepository subareaRepository) {
        this.subareaRepository = subareaRepository;
    }

    @Override
    public boolean isExisted(String s) {
        return subareaRepository.existsById(s);
    }
}
