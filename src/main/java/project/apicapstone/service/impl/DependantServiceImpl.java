package project.apicapstone.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.account.PagingFormatAccountDto;
import project.apicapstone.dto.dependant.CreateDependantDto;
import project.apicapstone.dto.dependant.PagingFormatDependantDto;
import project.apicapstone.entity.Dependant;
import project.apicapstone.repository.DepartmentRepository;
import project.apicapstone.repository.DependantRepository;
import project.apicapstone.service.DependantService;

import java.util.List;

@Service
public class DependantServiceImpl implements DependantService {
    private DependantRepository dependantRepository;

    public DependantServiceImpl(DependantRepository dependantRepository) {
        this.dependantRepository = dependantRepository;
    }

    @Override
    public List<Dependant> findAll() {
        return dependantRepository.findAll();
    }

    @Override
    public Page<Dependant> findAllDependant(Pageable pageable) {
        return dependantRepository.findAllDependant(pageable);
    }

    @Override
    public Object pagingFormat(Page<Dependant> dependantPage) {
        PagingFormatDependantDto dto = new PagingFormatDependantDto();
        dto.setPageSize(dependantPage.getSize());
        dto.setTotalRecordCount(dependantPage.getTotalElements());
        dto.setPageNumber(dependantPage.getNumber());
        dto.setRecords(dependantPage.toList());
        return dto;
    }

    @Override
    public Dependant createDependant(CreateDependantDto dto) {
        Dependant newDependant = new Dependant();
        newDependant.setDependantId(dto.getDependantId());
        newDependant.setDependantName(dto.getDependantName());
        newDependant.setAddress(dto.getAddress());
        newDependant.setDateBirth(dto.getDateBirth());
        newDependant.setGender(dto.getGender());
        newDependant.setPhone(dto.getPhone());
        newDependant.setNationality(dto.getNationality());

        return dependantRepository.save(newDependant);
    }

    @Override
    public boolean isExisted(String s) {
        return dependantRepository.existsById(s);
    }
}
