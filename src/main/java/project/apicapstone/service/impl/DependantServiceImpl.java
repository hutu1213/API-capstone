package project.apicapstone.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.dependant.CreateDependantDto;
import project.apicapstone.dto.dependant.PagingFormatDependantDto;
import project.apicapstone.dto.dependant.UpdateDependantDto;
import project.apicapstone.entity.Dependant;
import project.apicapstone.entity.Employee;

import project.apicapstone.repository.DependantRepository;
import project.apicapstone.repository.EmployeeRepository;
import project.apicapstone.service.DependantService;

import java.util.List;

@Service
public class DependantServiceImpl implements DependantService {
    private DependantRepository dependantRepository;
    private EmployeeRepository employeeRepository;

    public DependantServiceImpl(DependantRepository dependantRepository, EmployeeRepository employeeRepository) {
        this.dependantRepository = dependantRepository;
        this.employeeRepository = employeeRepository;
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
        newDependant.setMonthOfBirth(dto.getDateBirth().getMonthValue());
        newDependant.setDayOfBirth(dto.getDateBirth().getDayOfMonth());
        newDependant.setRelationship(dto.getRelationship());
        Employee employee = employeeRepository.getById(dto.getEmployeeId());
        newDependant.setEmployee(employee);
        return dependantRepository.save(newDependant);
    }

    @Override
    public boolean isExisted(String s) {
        return dependantRepository.existsById(s);
    }

    @Override
    public void deleteById(String id) {
        dependantRepository.deleteById(id);
    }

    @Override
    public void updateDependant(UpdateDependantDto dto, String dependantId) {
        Dependant updateDependant = dependantRepository.getById(dependantId);
        updateDependant.setDependantName(dto.getDependantName());
        updateDependant.setAddress(dto.getAddress());
        updateDependant.setDateBirth(dto.getDateBirth());
        updateDependant.setGender(dto.getGender());
        updateDependant.setPhone(dto.getPhone());
        updateDependant.setNationality(dto.getNationality());
        updateDependant.setMonthOfBirth(dto.getDateBirth().getMonthValue());
        updateDependant.setDayOfBirth(dto.getDateBirth().getDayOfMonth());
        updateDependant.setRelationship(dto.getRelationship());
        Employee employee = employeeRepository.getById(dto.getEmployeeId());
        updateDependant.setEmployee(employee);
        dependantRepository.save(updateDependant);
    }

    @Override
    public Dependant findDependantById(String id) {
        return dependantRepository.getById(id);
    }

    @Override
    public List<Dependant> findDependantByNameOrId(String paramSearch) {
        return dependantRepository.findDependantsByNameOrId(paramSearch);
    }

    @Override
    public Page<Dependant> search(String paramSearch, Pageable pageable) {
        return dependantRepository.search(paramSearch, pageable);
    }

    @Override
    public Page<Dependant> getByEmployeeId(String id, Pageable pageable) {
        return dependantRepository.getAllByEmployeeEmployeeId(id,pageable);
    }

    @Override
    public List<Dependant> checkBirthDate(int dayOfMonth, int monthValue) {
        return dependantRepository.findByDayOfBirthAndMonthOfBirth(dayOfMonth,monthValue);
    }
}
