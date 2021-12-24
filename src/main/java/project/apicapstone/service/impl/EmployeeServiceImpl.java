package project.apicapstone.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.employee.CreateEmployeeDto;
import project.apicapstone.dto.employee.PagingFormatEmployeeDto;
import project.apicapstone.dto.employee.UpdateEmployeeDto;
import project.apicapstone.entity.Employee;
import project.apicapstone.entity.Title;
import project.apicapstone.entity.util.WorkingStatus;
import project.apicapstone.repository.EmployeeRepository;
import project.apicapstone.repository.TitleRepository;
import project.apicapstone.service.EmployeeService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;

import java.util.Date;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.next;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private TitleRepository titleRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, TitleRepository titleRepository) {
        this.employeeRepository = employeeRepository;
        this.titleRepository = titleRepository;
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAllEmp(pageable);
    }

    @Override
    public List<Employee> findEmployeeByName(String employeeName) {
        List<Employee> employeeList = employeeRepository.findEmployeesByEmployeeNameContains(employeeName);
        if (employeeList.size() == 0) {
            //throw new ResourceNotFoundException("");
            throw new IllegalStateException("Name not exists!");
        }
        return employeeList;
    }

    @Override
    public Employee addNewEmployee(CreateEmployeeDto dto) {
        Employee addEmployee = new Employee();
        addEmployee.setEmployeeId(dto.getEmployeeId());
        addEmployee.setEmployeeName(dto.getEmployeeName());
        addEmployee.setDateBirth(dto.getDateBirth());
        addEmployee.setPlaceBirth(dto.getPlaceBirth());
        addEmployee.setPhone(dto.getPhone());
        addEmployee.setGender(dto.getGender());
        addEmployee.setAddress(dto.getAddress());
        addEmployee.setEmail(dto.getEmail());
        addEmployee.setNationality(dto.getNationality());
        addEmployee.setReligion(dto.getReligion());
        addEmployee.setEthnic(dto.getEthnic());
        addEmployee.setAcademicLevel(dto.getAcademicLevel());
        addEmployee.setBank(dto.getBank());
        addEmployee.setBankAccountNo(dto.getBankAccountNo());
        addEmployee.setTaxIdentificationNo(dto.getTaxIdentificationNo());
        addEmployee.setMaritalStatus(dto.getMaritalStatus());
        addEmployee.setWorkingStatus(dto.getWorkingStatus());
        addEmployee.setAvatar(dto.getAvatar());
       // addEmployee.setCreateDate(dto.getCreateDate());
        addEmployee.setCreateDate(LocalDate.now());
//        LocalDate date = getEndDateFromWeek();
        Title title = titleRepository.getById(dto.getTitleId());
        addEmployee.setTitle(title);
        return employeeRepository.save(addEmployee);
    }


    @Override
    public List<Employee> findEmployeeByNameOrId(String paramSearch) {
        List<Employee> listSearch = employeeRepository.findEmployeesByNameOrId(paramSearch);
        return listSearch;
    }

    @Override
    public boolean isExisted(String id) {
        return employeeRepository.existsById(id);
    }

    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee findEmployeeById(String id) {
        return employeeRepository.getById(id);
    }

    @Override
    public void updateEmployee(UpdateEmployeeDto dto, String id) {
        Employee updateEmployee = employeeRepository.getById(id);
        updateEmployee.setEmployeeName(dto.getEmployeeName());
        updateEmployee.setDateBirth(dto.getDateBirth());
        updateEmployee.setPlaceBirth(dto.getPlaceBirth());
        updateEmployee.setPhone(dto.getPhone());
        updateEmployee.setGender(dto.getGender());
        updateEmployee.setAddress(dto.getAddress());
        updateEmployee.setEmail(dto.getEmail());
        updateEmployee.setNationality(dto.getNationality());
        updateEmployee.setReligion(dto.getReligion());
        updateEmployee.setEthnic(dto.getEthnic());
        updateEmployee.setBank(dto.getBank());
        updateEmployee.setBankAccountNo(dto.getBankAccountNo());
        updateEmployee.setTaxIdentificationNo(dto.getTaxIdentificationNo());
        updateEmployee.setAcademicLevel(dto.getAcademicLevel());
        updateEmployee.setMaritalStatus(dto.getMaritalStatus());
        updateEmployee.setWorkingStatus(dto.getWorkingStatus());
        updateEmployee.setAvatar(dto.getAvatar());
        updateEmployee.setCreateDate(LocalDate.now());
        updateEmployee.setTitle(titleRepository.getById(dto.getTitleId()));
        employeeRepository.save(updateEmployee);
    }




    @Override
    public PagingFormatEmployeeDto pagingFormat(Page<Employee> employeePage) {
        PagingFormatEmployeeDto dto = new PagingFormatEmployeeDto();
        dto.setPageSize(employeePage.getSize());
        dto.setTotalRecordCount(employeePage.getTotalElements());
        dto.setPageNumber(employeePage.getNumber());
        dto.setRecords(employeePage.toList());
        return dto;
    }

}
