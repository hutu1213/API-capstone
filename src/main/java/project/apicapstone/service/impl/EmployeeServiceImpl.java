package project.apicapstone.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.employee.CreateEmployeeDto;
import project.apicapstone.dto.employee.PagingFormatEmployeeDto;
import project.apicapstone.dto.employee.UpdateEmployeeDto;
import project.apicapstone.entity.Employee;
import project.apicapstone.entity.Title;
import project.apicapstone.repository.EmployeeRepository;
import project.apicapstone.repository.TitleRepository;
import project.apicapstone.service.EmployeeService;

import java.util.List;

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
        addEmployee.setFrontIdentityCard(dto.getFrontIdentityCard());
        addEmployee.setBackIdentityCard(dto.getBackIdentityCard());
        addEmployee.setGender(dto.getGender());
        addEmployee.setAddress(dto.getAddress());
        addEmployee.setEmail(dto.getEmail());
        addEmployee.setNationality(dto.getNationality());
        addEmployee.setReligion(dto.getReligion());
        addEmployee.setCountryOfCitizenship(dto.getCountryOfCitizenship());
        addEmployee.setAcademicLevel(dto.getAcademicLevel());
        addEmployee.setMaritalStatus(dto.getMaritalStatus());
        addEmployee.setWorkingStatus(dto.getWorkingStatus());
        Title title = titleRepository.getById(dto.getTitleId());
        addEmployee.setTitle(title);
        return employeeRepository.save(addEmployee);
    }


    @Override
    public List<Employee> findEmployeeByNameOrId(String paramSearch) {
//        List<Employee> employeeList = employeeRepository.findEmployeesByEmployeeNameContains(paramSearch);
//        if (employeeList.size() == 0) {
//            //throw new ResourceNotFoundException("");
//            throw new IllegalStateException("Name not exists!");
//        }
//        List<Employee> employee = employeeRepository.findEmployeeByIdEmployeeContaining(paramSearch);
//        if (employee==null) {
//            //throw new ResourceNotFoundException("");
//            throw new IllegalStateException("Id not exists!");
//
//        }
        List<Employee> listSearch = employeeRepository.findEmployeesByNameOrId(paramSearch);
        if (listSearch.size() == 0) {
            throw new IllegalStateException("Not found !");
        }
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
//        Employee employee = employeeRepository.getById(id);
//        if(employee==null){
//            throw new IllegalStateException("Not found !");
//        }
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee findEmployeeById(String id) {
        return employeeRepository.getById(id);
    }

    @Override
    public void updateEmployee(UpdateEmployeeDto dto, String id) {
        Employee updateEmployee = employeeRepository.getById(id);
        //employee.setEmployeeId(dto.getEmployeeId());
        updateEmployee.setEmployeeName(dto.getEmployeeName());
        updateEmployee.setDateBirth(dto.getDateBirth());
        updateEmployee.setPlaceBirth(dto.getPlaceBirth());
        updateEmployee.setPhone(dto.getPhone());
        updateEmployee.setFrontIdentityCard(dto.getFrontIdentityCard());
        updateEmployee.setBackIdentityCard(dto.getBackIdentityCard());
        updateEmployee.setGender(dto.getGender());
        updateEmployee.setAddress(dto.getAddress());
        updateEmployee.setEmail(dto.getEmail());
        updateEmployee.setNationality(dto.getNationality());
        updateEmployee.setReligion(dto.getReligion());
        updateEmployee.setCountryOfCitizenship(dto.getCountryOfCitizenship());
        updateEmployee.setAcademicLevel(dto.getAcademicLevel());
        updateEmployee.setMaritalStatus(dto.getMaritalStatus());
        updateEmployee.setWorkingStatus(dto.getWorkingStatus());
        Title title = titleRepository.getById(dto.getTitleId());
        updateEmployee.setTitle(title);
        employeeRepository.save(updateEmployee);
    }


    //    public Account addRole(AddRoleDto dto) {
//        Role role = roleRepository.getById(dto.getRoleId());
//        Account account = accountRepository.getById(dto.getAccountId());
//
//        account.addRole(role);
//
//        return accountRepository.save(account);
//    }
    @Override
    public PagingFormatEmployeeDto pagingFormat(Page<Employee> employeePage) {
        PagingFormatEmployeeDto dto = new PagingFormatEmployeeDto();
        dto.setPageSize(employeePage.getSize());
        dto.setTotalRecordCount(employeePage.getTotalElements());
        dto.setPageNumber(employeePage.getNumber());
        dto.setRecords(employeePage.toList());
        return dto;
    }


//    delete(Long id){
//        boolean exists=employeeRepository.existsById(id);
//        if(!exists){
//            throw new IllegalStateException("Id not exists!");
//        }
//        return employeeRepository.deleteById(id);
//    }
}
